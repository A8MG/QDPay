package com.a8.qdm.config.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.a8.qdm.Page;
import com.a8.qdm.config.action.bean.GameWebBean;
import com.a8.qdm.config.dao.ActionDao;
import com.a8.qdm.config.dao.ChannelGameDao;
import com.a8.qdm.config.dao.CpGameDao;
import com.a8.qdm.config.dao.GameActionDao;
import com.a8.qdm.config.dao.GameDao;
import com.a8.qdm.config.dao.GamePayDao;
import com.a8.qdm.config.dao.bean.ChannelGame;
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
	 * 注入channelGameDao
	 */
	private ChannelGameDao channelGameDao;

	/**
	 * 注入gamePayDao
	 */
	private GamePayDao gamePayDao;

	/**
	 * 根据渠道ID查询产品集合
	 * 
	 * @param channelId
	 *            渠道ID
	 * @return 产品集合
	 * @throws Exception
	 */
	public List<Game> queryGameListById(String channelId) throws Exception {
		return gameDao.queryGameListById(channelId);
	}

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
		return gameDao.queryGameList(search, page);
	}

	/**
	 * 添加产品
	 * 
	 * @param game
	 *            产品
	 * @param cpGame
	 *            合作方与产品关联
	 * @param channelGameList
	 *            渠道与产品关联集合
	 * @param gamePayList
	 *            产品与支付方式关联集合
	 * @throws Exception
	 */
	@Transactional
	public void addGame(Game game, CpGame cpGame,
			List<ChannelGame> channelGameList, List<GamePay> gamePayList)
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

		// 添加产品与渠道关系
		if (!channelGameList.isEmpty()) {
			channelGameDao.addChannelGame(channelGameList);
		}

		// 添加产品与支付方式关系
		gamePayDao.addGamePay(gamePayList);
	}

	/**
	 * 修改产品
	 * 
	 * @param game
	 *            产品
	 * @param channelGameList
	 *            渠道与产品关联集合
	 * @param gamePayList
	 *            产品与支付方式关联集合
	 * @throws Exception
	 */
	@Transactional
	public void updateGame(Game game, List<ChannelGame> channelGameList,
			List<GamePay> gamePayList) throws Exception {
		String[] gameId = { game.getGameId() };

		// 修改产品
		gameDao.updateGame(game);

		// 删除原关联渠道
		channelGameDao.deleteChannelGame(gameId);

		// 添加新关联渠道
		if (!channelGameList.isEmpty()) {
			channelGameDao.addChannelGame(channelGameList);
		}

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

		// 删除产品与渠道关系
		channelGameDao.deleteChannelGame(gameId);

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

	public ChannelGameDao getChannelGameDao() {
		return channelGameDao;
	}

	public void setChannelGameDao(ChannelGameDao channelGameDao) {
		this.channelGameDao = channelGameDao;
	}

	public GamePayDao getGamePayDao() {
		return gamePayDao;
	}

	public void setGamePayDao(GamePayDao gamePayDao) {
		this.gamePayDao = gamePayDao;
	}
}
