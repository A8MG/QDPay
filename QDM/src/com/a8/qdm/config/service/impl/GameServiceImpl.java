package com.a8.qdm.config.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.a8.qdm.Page;
import com.a8.qdm.config.action.bean.GameWebBean;
import com.a8.qdm.config.dao.ActionDao;
import com.a8.qdm.config.dao.CpGameDao;
import com.a8.qdm.config.dao.GameActionDao;
import com.a8.qdm.config.dao.GameDao;
import com.a8.qdm.config.dao.GamePayDao;
import com.a8.qdm.config.dao.bean.CpGame;
import com.a8.qdm.config.dao.bean.Game;
import com.a8.qdm.config.dao.bean.GameAction;
import com.a8.qdm.config.dao.bean.GamePay;
import com.a8.qdm.config.service.GameService;

/**
 * 产品Service实现
 * 
 * @author lund
 *
 */
public class GameServiceImpl implements GameService {

	/**
	 * 注入gameDao
	 */
	private GameDao gameDao;

	/**
	 * 注入actionDao
	 */
	private ActionDao actionDao;

	/**
	 * 注入gameActionDao
	 */
	private GameActionDao gameActionDao;

	/**
	 * 注入cpGameDao
	 */
	private CpGameDao cpGameDao;

	/**
	 * 注入gamePayDao
	 */
	private GamePayDao gamePayDao;

	/**
	 * 根据产品ID查询
	 * 
	 * @param gameId
	 *            产品ID
	 * @return 产品
	 * @throws Exception
	 */
	public GameWebBean queryGameById(String gameId) throws Exception {
		return gameDao.queryGameById(gameId);
	}

	/**
	 * 根据合作方查询产品
	 * 
	 * @param cpId
	 *            合作方ID
	 * @return 产品集合
	 * @throws Exception
	 */
	public List<Game> queryAllGame(String cpId) throws Exception {
		return gameDao.queryAllGame(cpId);
	}

	/**
	 * 查询产品数量
	 * 
	 * @param search
	 *            查询条件
	 * @return 数量
	 * @throws Exception
	 */
	public int queryGameCount(GameWebBean search) throws Exception {
		return gameDao.queryGameCount(search);
	}

	/**
	 * 查询产品列表
	 * 
	 * @param search
	 *            查询条件
	 * @param page
	 *            分页
	 * @return 产品列表
	 * @throws Exception
	 */
	public List<GameWebBean> queryGameList(GameWebBean search, Page page)
			throws Exception {
		List<GameWebBean> gameServiceBeanList = new ArrayList<GameWebBean>();

		// 查询产品列表
		gameServiceBeanList = gameDao.queryGameList(search, page);

		// 设置支付名称
		for (GameWebBean gameServiceBean : gameServiceBeanList) {
			List<String> payNameList = gamePayDao.queryPayName(gameServiceBean
					.getGameId());
			if (payNameList != null && !payNameList.isEmpty()) {
				String payName = payNameList.toString();
				gameServiceBean.setPayName(payName.substring(1,
						payName.length() - 1));
			}
		}

		return gameServiceBeanList;
	}

	/**
	 * 添加产品
	 * 
	 * @param game
	 *            产品
	 * @param cpGame
	 *            合作方与产品关联
	 * @param gamePayList
	 *            产品与支付方式关联集合
	 * @throws Exception
	 */
	@Transactional
	public void addGame(Game game, CpGame cpGame, List<GamePay> gamePayList)
			throws Exception {

		// 构建行为
		GameAction gameAction = new GameAction();
		gameAction.setActionId("A" + game.getGameId());
		gameAction.setGameId(game.getGameId());

		// 添加产品
		gameDao.addGame(game);

		// 添加行为
		actionDao.addAction("A" + game.getGameId());

		// 添加产品与行为关系
		gameActionDao.addGameAction(gameAction);

		// 添加产品与合作方关系
		cpGameDao.addCpGame(cpGame);

		// 添加产品与支付方式关系
		gamePayDao.addGamePay(gamePayList);
	}

	/**
	 * 修改产品
	 * 
	 * @param game
	 *            产品
	 * @param gamePayList
	 *            产品与支付方式关联集合
	 * @throws Exception
	 */
	@Transactional
	public void updateGame(Game game, List<GamePay> gamePayList)
			throws Exception {
		String[] gameId = { game.getGameId() };

		// 修改产品
		gameDao.updateGame(game);

		// 删除原关联支付
		gamePayDao.deleteGamePay(gameId);

		// 添加新关联支付
		gamePayDao.addGamePay(gamePayList);
	}

	/**
	 * 删除产品
	 * 
	 * @param gameId
	 *            产品ID数组
	 * @throws Exception
	 */
	@Transactional
	public void deleteGame(String[] gameId) throws Exception {
		String[] actionId = new String[gameId.length];

		// 删除产品
		gameDao.deleteGame(gameId);

		// 删除产品与合作方关系
		cpGameDao.deleteCpGame(gameId);

		// 删除产品与支付方式关系
		gamePayDao.deleteGamePay(gameId);

		// 删除产品与行为关系
		gameActionDao.deleteGameAction(gameId);

		// 删除行为
		for (int i = 0; i < gameId.length; i++) {
			actionId[i] = "A" + gameId[i];
		}
		actionDao.deleteAction(actionId);
	}

	public GameDao getGameDao() {
		return gameDao;
	}

	public void setGameDao(GameDao gameDao) {
		this.gameDao = gameDao;
	}

	public ActionDao getActionDao() {
		return actionDao;
	}

	public void setActionDao(ActionDao actionDao) {
		this.actionDao = actionDao;
	}

	public GameActionDao getGameActionDao() {
		return gameActionDao;
	}

	public void setGameActionDao(GameActionDao gameActionDao) {
		this.gameActionDao = gameActionDao;
	}

	public CpGameDao getCpGameDao() {
		return cpGameDao;
	}

	public void setCpGameDao(CpGameDao cpGameDao) {
		this.cpGameDao = cpGameDao;
	}

	public GamePayDao getGamePayDao() {
		return gamePayDao;
	}

	public void setGamePayDao(GamePayDao gamePayDao) {
		this.gamePayDao = gamePayDao;
	}
}
