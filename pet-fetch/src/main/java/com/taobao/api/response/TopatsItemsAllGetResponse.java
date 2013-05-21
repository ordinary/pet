package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.domain.Task;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.topats.items.all.get response.
 * 
 * @author auto create
 * @since 1.0, null
 */
public class TopatsItemsAllGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 8143598285411599863L;

	/** 
	 * 创建任务信息。里面只包含task_id和created
	 */
	@ApiField("task")
	private Task task;

	public void setTask(Task task) {
		this.task = task;
	}
	public Task getTask( ) {
		return this.task;
	}

}
