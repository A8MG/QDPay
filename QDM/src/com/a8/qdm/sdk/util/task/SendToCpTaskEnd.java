package com.a8.qdm.sdk.util.task;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 银联：向Cp发送消息结束任务
 * 
 * @author lund
 *
 */
public class SendToCpTaskEnd extends TimerTask {

	/**
	 * 定时器
	 */
	private Timer timer;

	/**
	 * 构造函数
	 * 
	 * @param timer
	 *            定时器
	 */
	public SendToCpTaskEnd(Timer timer) {
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
