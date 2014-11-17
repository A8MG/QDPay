package com.a8.qdm.sdk.util.task;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 关闭银联查询任务
 * 
 * @author lund
 *
 */
public class QueryUpmpTaskEnd extends TimerTask {

	/**
	 * 定时器
	 */
	private Timer timer;

	/**
	 * 构造方法
	 * 
	 * @param timer
	 *            定时器
	 */
	public QueryUpmpTaskEnd(Timer timer) {
		this.timer = timer;
	}

	/**
	 * 执行任务
	 */
	@Override
	public void run() {
		timer.cancel();
	}
}
