package com.a8.qdm.query.action;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.a8.qdm.config.dao.bean.Channel;
import com.a8.qdm.query.action.bean.ActiveWebBean;
import com.a8.qdm.query.service.ActiveService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户行为Action
 * 
 * @author lund
 *
 */
public class ActiveAction extends ActionSupport {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 6755850222930214772L;

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(ActiveAction.class);

	/**
	 * activeService接口注入
	 */
	private ActiveService activeService;

	/**
	 * 查询条件：渠道
	 */
	private String channelId;
	
	/**
	 * 所有渠道
	 */
	private List<Channel> channelList;

	/**
	 * 查询条件：游戏
	 */
	private String gameId;

	/**
	 * 查询条件：日期（天）
	 */
	private String dayTime;

	/**
	 * 用户活跃统计类
	 */
	private ActiveWebBean activeWebBean;

	/**
	 * 查询用户行为
	 */
	public void queryActiveNo() throws Exception {

		// 入口日志
		log.info("---------------queryActiveNo start---------------");
		Map<String, Object> session = ActionContext.getContext().getSession();
		String authId = session.get("channelId").toString();
		
		// 管理员
		if ("".equals(authId)) {
			
		} else {

		}

		// 出口日志
		log.info("---------------queryActiveNo end---------------");
	}

	public ActiveService getActiveService() {
		return activeService;
	}

	public void setActiveService(ActiveService activeService) {
		this.activeService = activeService;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public List<Channel> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<Channel> channelList) {
		this.channelList = channelList;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getDayTime() {
		return dayTime;
	}

	public void setDayTime(String dayTime) {
		this.dayTime = dayTime;
	}

	public ActiveWebBean getActiveWebBean() {
		return activeWebBean;
	}

	public void setActiveWebBean(ActiveWebBean activeWebBean) {
		this.activeWebBean = activeWebBean;
	}
}
