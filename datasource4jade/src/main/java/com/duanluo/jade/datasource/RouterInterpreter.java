package com.duanluo.jade.datasource;

import java.text.MessageFormat;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQLType;
import net.paoding.rose.jade.statement.Interpreter;
import net.paoding.rose.jade.statement.StatementRuntime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.util.Assert;

import com.duanluo.jade.db.JadeDataSourceConfig;
import com.duanluo.jade.db.JadeTablePartitionConfig;
import com.duanluo.jade.domain.ConfigItem;
import com.duanluo.jade.domain.Configurations;
import com.meidusa.amoeba.parser.dbobject.Column;
import com.meidusa.amoeba.parser.dbobject.Table;

//按Spring语义规定，Order值越高该解释器越后执行
@Order(9000)
public class RouterInterpreter implements Interpreter {

    private static final Log logger = LogFactory.getLog(RouterInterpreter.class);

    private String doHashRouter(String pattern, int partitions, String column, Object columnValue) {
        long longValue = -1;
        if (columnValue instanceof Number) {
            longValue = ((Number) columnValue).longValue();
        } else {
            try {
                // 转换成字符串处理
                longValue = Long.parseLong(String.valueOf(columnValue));
            } catch (NumberFormatException e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Column \'" + column + "\' must be number, but: " + columnValue);
                }

                throw new BadSqlGrammarException("HashRouter.convert", "Column \'" + column + "\' must be number, but: " + columnValue, null);
            }
        }

        int value = (int) (longValue % partitions);
        String name = MessageFormat.format(pattern, value);

        // 输出日志
        if (logger.isDebugEnabled()) {
            logger.debug("Routing on [" + column + " = " + columnValue + ", " + columnValue.getClass() + "]: " + name);
        }

        return name;
    }

    // 查找散表参数
    protected static Object findShardParamValue(SQLParseInfo parseInfo, Column column, StatementRuntime runtime) {
        int shardByIndex = runtime.getMetaData().getShardByIndex();

        Object value = null;
        if (shardByIndex != -1) {
            value = runtime.getMetaData().getSQLParamAt(shardByIndex);
            if (logger.isDebugEnabled()) {
                logger.debug("get shard param value '" + value + "' by @ShardBy (" + shardByIndex + ")");
            }
            return value;
        }

        if (!parseInfo.containsParam(column)) {
            // 如果针对该列进行散表，则必须包含该列作为查询条件。
            throw new BadSqlGrammarException("interpreter.findShardParamValue", "SQL [" + parseInfo.getSQL() + "] Query without shard parameter: " // NL
                    + column.getSql(), null);
        }
        // 获取语句中的散表参数
        value = parseInfo.getParam(column);
        if (value == null) {
            int index = parseInfo.getColumnIndex(column) - 1;
            if (index < 0 && index >= runtime.getMetaData().getParameterCount()) {
                // 如果针对该列进行散表，则必须包含该列作为查询条件。
                throw new BadSqlGrammarException("interpreter.findShardParamValue", "SQL [" + parseInfo.getSQL() + "] Query without shard parameter: " // NL
                        + column.getSql(), null);
            }

            value = runtime.getArgs()[index];
            if (logger.isDebugEnabled()) {
                logger.debug("find shard param value '" + value + "' by column's arg " + column.getName() + " [index=" + index + " (beginwiths 0)]");
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("find shard param value '" + value + "' by column " + column.getName() + " [constants]");
        }

        return value;

    }

    @Override
    public void interpret(StatementRuntime runtime) {

        Assert.notNull(runtime.getParameters(), "need parametersAsArray prepared before invoking this interpreter!");
        SQLParseInfo parseInfo = SQLParseInfo.getParseInfo(runtime.getSQL());
        // 从查询的数据表获取路由配置。
        Table[] tables = parseInfo.getTables();

        Table partitionTable = null;
        JadeTablePartitionConfig tblPartitionCfg = null;
        Class<?> daoClass = runtime.getMetaData().getDAOMetaData().getDAOClass();

        String catalog = daoClass.getAnnotation(DAO.class).catalog();
        //
        if (tables != null) {
            int beginIndex = 0;
            if (parseInfo.isInsert() && tables.length > 1) {
                // INSERT ... SELECT 查询
                beginIndex = 1;
            }

            // 查找散表配置
            for (int i = beginIndex; i < tables.length; i++) {

                ConfigItem configItem = Configurations.getInstance().getConfigById(catalog);
                if (configItem == null) {
                    break;
                }
                JadeDataSourceConfig datasourceConfig = runtime.getMetaData().getSQLType() == SQLType.WRITE ? configItem.getMasterDBConfig() : configItem.getSlaverDBConfig();

                if (configItem == null || datasourceConfig == null || datasourceConfig.getTablePartitionMap() == null || datasourceConfig.getTablePartitionMap().size() < 1) {
                    break;
                }
                JadeTablePartitionConfig p = datasourceConfig.getTablePartitionMap().get(tables[i].getName());
                if (p != null) {
                    partitionTable = tables[i];
                    tblPartitionCfg = p;
                    break;
                }
            }
        }
        if (tblPartitionCfg == null) {
            return;
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("Find routing info: " + tblPartitionCfg.getTableName() + ", " + tblPartitionCfg.getByColumn());
            }
        }
        String forwardTableName = null;

        if (tblPartitionCfg != null) {
            // 用语句信息的常量进行散表。
            Column column = new Column();
            column.setName(tblPartitionCfg.getByColumn());
            column.setTable(partitionTable);
            Object columnValue = null;

            // columnValue = findShardParamValue(parseInfo, column,
            // parametersAsMap, parametersAsArray);
            columnValue = findShardParamValue(parseInfo, column, runtime);
            if (columnValue == null) {
                throw new BadSqlGrammarException("sharding", parseInfo.getSQL(), null);
            }

            // 获得散表的名称 (注： 只考虑Hash散列的情况)
            forwardTableName = doHashRouter(tblPartitionCfg.getTargetPattern(), tblPartitionCfg.getPartitions(), tblPartitionCfg.getByColumn(), columnValue);
        }

        String byTableName = tblPartitionCfg != null ? tblPartitionCfg.getTableName() : null;
        final String sqlRewrited;
        if ((forwardTableName != null) && !forwardTableName.equals(byTableName)) {

            // 使用 SqlRewriter 拆分语句，进行所需的查找和替换。
            sqlRewrited = SqlRewriter.rewriteSqlTable(runtime.getSQL(), byTableName, forwardTableName);

            // 输出重写日志
            if (logger.isDebugEnabled()) {
                logger.debug("Rewriting SQL: \n  From: " + runtime.getSQL() + "\n  To:   " + sqlRewrited);
            }
        } else {
            sqlRewrited = runtime.getSQL();
        }
        runtime.setSQL(sqlRewrited);
        return;
    }
}
