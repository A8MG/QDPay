package com.a8.qdm.query.action;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.a8.qdm.InitAction;
import com.a8.qdm.config.dao.bean.Action;
import com.a8.qdm.config.dao.bean.Game;
import com.a8.qdm.config.service.ActionService;
import com.a8.qdm.config.service.GameService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 访问数据
 * 
 * @author lund
 *
 */
public class ActionAction extends InitAction {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -3570748596515884390L;

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(ActionAction.class);

	/**
	 * actionService接口注入
	 */
	private ActionService actionService;

	/**
	 * gameService接口注入
	 */
	private GameService gameService;

	/**
	 * 查询条件
	 */
	private String search = "";

	/**
	 * 产品集合
	 */
	private List<Game> gameList;

	/**
	 * 行为列表
	 */
	private List<Action> actionList;

	/**
	 * 查询访问数据
	 */
	public void queryImpl() throws Exception {

		// 入口日志
		log.info("---------------queryActionList start---------------");
		Map<String, Object> session = ActionContext.getContext().getSession();

		String cpId = session.get("cpId").toString();
		log.info("合作方ID：" + cpId);
		gameList = gameService.queryAllGame(cpId);
		log.info("合作方" + cpId + "下的产品：" + gameList);
		log.info("查询条件：" + search);
		page.setTotalCount(actionService.queryActionCount(search, cpId));
		page.setPageCount();
		page.setStartIndex();
		log.info("分页：" + page);
		actionList = actionService.queryActionList(search, cpId, page);

		// 出口日志
		log.info("---------------queryActionList end---------------");
	}

	public ActionService getActionService() {
		return actionService;
	}

	public void setActionService(ActionService actionService) {
		this.actionService = actionService;
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<Game> getGameList() {
		return gameList;
	}

	public void setGameList(List<Game> gameList) {
		this.gameList = gameList;
	}

	public List<Action> getActionList() {
		return actionList;
	}

	public void setActionList(List<Action> actionList) {
		this.actionList = actionList;
	}
}
