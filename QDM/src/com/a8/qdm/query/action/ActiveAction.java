package com.a8.qdm.query.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.a8.qdm.config.dao.bean.Channel;
import com.a8.qdm.config.dao.bean.Game;
import com.a8.qdm.config.service.ChannelService;
import com.a8.qdm.config.service.GameService;
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
	 * channelService接口注入
	 */
	private ChannelService channelService;

	/**
	 * 注入gameService
	 */
	private GameService gameService;

	/**
	 * 查询条件：渠道
	 */
	private String channelId;

	/**
	 * 所有渠道
	 */
	private List<Channel> channelList = new ArrayList<Channel>();

	/**
	 * 查询条件：游戏
	 */
	private String gameId;

	/**
	 * 与渠道关联的游戏集合
	 */
	private List<Game> gameList = new ArrayList<Game>();

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
	public String queryActiveNo() {

		// 入口日志
		log.info("---------------queryActiveNo start---------------");
		Map<String, Object> session = ActionContext.getContext().getSession();
		Map<String, String> map = new HashMap<String, String>();
		String curDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String authId = session.get("channelId").toString();

		try {
			// 查询条件：渠道
			if ("".equals(authId)) {

				// 管理员
				channelList = channelService.queryAllChannel();
			} else {

				// 渠道
				Channel channel = channelService.queryChannelById(authId);
				if (channel != null) {
					channelList.add(channel);
				}
			}
			if (!channelList.isEmpty() && channelId == null) {
				channelId = channelList.get(0).getChannelId();
			}
			log.info("渠道：" + channelList);

			// 查询条件：游戏
			if (channelId != null) {
				gameList = gameService.queryGameListById(channelId);
				if (!gameList.isEmpty() && gameId == null) {
					gameId = gameList.get(0).getGameId();
				}
			}
			log.info("产品：" + gameList);

			// 查询条件：日期
			if (dayTime == null) {
				dayTime = curDate;
			}

			// 根据查询条件查询
			map.put("channelId", channelId);
			map.put("gameId", gameId);
			map.put("dayTime", dayTime);
			activeWebBean = activeService.queryActiveNo(map);
			log.info("用户活跃数据：" + activeWebBean);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------queryActiveNo end---------------");

		return SUCCESS;
	}

	public ActiveService getActiveService() {
		return activeService;
	}

	public void setActiveService(ActiveService activeService) {
		this.activeService = activeService;
	}

	public ChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(ChannelService channelService) {
		this.channelService = channelService;
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
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

	public List<Game> getGameList() {
		return gameList;
	}

	public void setGameList(List<Game> gameList) {
		this.gameList = gameList;
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
