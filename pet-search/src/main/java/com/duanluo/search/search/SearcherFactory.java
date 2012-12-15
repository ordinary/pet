package com.duanluo.search.search;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import net.paoding.rose.scanning.context.RoseAppContext;

import org.apache.cassandra.cli.CliParser.newColumnFamily_return;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.search.Similarity;
import org.apache.lucene.util.Version;

import com.chenlb.mmseg4j.analysis.ComplexAnalyzer;
import com.duanluo.search.annotations.Search;
import com.duanluo.search.dataProvider.SubjectDataProvider;
import com.duanluo.search.index.BasicIndexReader;
import com.duanluo.search.index.BasicIndexReaderDecorator;
import com.duanluo.search.index.SubjectIndexableInterpreter;
import com.duanluo.search.search.BasicSearcher.SearcherConfig;
import com.duanluo.search.utils.SearchContants;
import com.duanluo.search.wrapper.SubjectWrapper;
import com.pet.core.domain.Subject;

import proj.zoie.impl.indexing.NoopReaderCache;
import proj.zoie.impl.indexing.ZoieConfig;

/**
 * 提供统一的搜索接口和入口
 * 
 * @author soca
 */
public class SearcherFactory {

	private static Properties properties;

	private static Map<String, Searcher> cache;
	private static ReentrantReadWriteLock lock;

	static {
		@SuppressWarnings("unused")
		RoseAppContext rose = new RoseAppContext();
		cache = new HashMap<String, Searcher>();
		lock = new ReentrantReadWriteLock();
		// 加载磁盘索引
		init();
	}

	// TODO 初始化工作
	private static void init() {

	}

	public static void startIndexService(String path) {
		try {
			properties = new Properties();
			InputStream in = new BufferedInputStream(new FileInputStream(path));
			properties.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		getSearcher(SubjectWrapper.class, Subject.class);
	}

	public static <M, MW> Searcher getSearcher(Class<MW> wrapperCls,
			Class<M> clz) {

		Searcher searcher = null;
		lock.readLock().lock();
		try {
			searcher = cache.get(wrapperCls.getName());
			if (searcher != null) {
				return (Searcher) searcher;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.readLock().unlock();
		}
		lock.writeLock().lock();
		try {
			searcher = createSearcher(wrapperCls);
			cache.put(wrapperCls.getName(), searcher);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.writeLock().unlock();
		}
		return (Searcher) searcher;
	}

	private static Searcher createSearcher(Class<?> cls)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		if (!cls.isAnnotationPresent(Search.class)) {
			return null;
		}
		Search searchAnnotation = cls.getAnnotation(Search.class);
		// set index path
		System.out.println("***" + searchAnnotation.indexPath());
		File file = new File(searchAnnotation.indexPath());

		ZoieConfig zoieConfig = new ZoieConfig();

		// 当内存索引中的文档数量超过配置的batch size或者时间超过设置的_delay的时候，就进行内存索引到硬盘索引的合并
		zoieConfig.setBatchSize(Integer.parseInt(properties.get(
				SearchContants.batchSize).toString()));
		zoieConfig.setBatchDelay(Integer.parseInt(properties.get(
				SearchContants.batchDelay).toString()));

//		zoieConfig.setAnalyzer(new StandardAnalyzer(Version.LUCENE_35));
		zoieConfig.setAnalyzer(new ComplexAnalyzer());
		Similarity similarity = (Similarity) Class.forName(
				searchAnnotation.similarity()).newInstance();
		zoieConfig.setSimilarity(similarity);
		zoieConfig.setVersionComparator(new Comparator<String>() {
			public int compare(String str1, String str2) {
				return str1.compareTo(str2);
			}
		});
		// 设置NoopReaderCache而不是defaultReaderCache保证启动的时候同步加载
		zoieConfig.setReadercachefactory(NoopReaderCache.FACTORY);
		if (cls.equals(SubjectWrapper.class)) {
			System.out.println("** subject wrapper **");
			SearcherConfig<BasicIndexReader, SubjectWrapper, Subject> searcherConfig = new SearcherConfig<BasicIndexReader, SubjectWrapper, Subject>();
			searcherConfig.file = file;
			// searcherConfig.modelParser = new SubjectModelParser();
			searcherConfig.interpreter = new SubjectIndexableInterpreter();
			searcherConfig.decorator = new BasicIndexReaderDecorator();
			searcherConfig.zoieConfig = zoieConfig;
			SubjectDataProvider dataProvider = new SubjectDataProvider(
					new Comparator<String>() {
						public int compare(String str1, String str2) {
							if (str1 == null) {
								return -1;
							} else if (str2 == null) {
								return 1;
							} else {
								return str1.compareTo(str2);
							}
						}
					});
			BasicSearcher<BasicIndexReader, SubjectWrapper, Subject> searcher = new SubjectSearcher<BasicIndexReader, SubjectWrapper, Subject>(
					searcherConfig, dataProvider);
			return searcher;
		}
		return null;
	}
}
