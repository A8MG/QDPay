package com.a8.qdm.config.dao;

import java.util.List;

import com.a8.qdm.Page;
import com.a8.qdm.config.action.bean.GameWebBean;
import com.a8.qdm.config.dao.bean.Game;

/**
 * 产品Dao
 * 
 * @author lund
 *
 */
public interface GameDao {

	/**
	 * 根据产品ID查询
	 * 
	 * @param gameId
	 *            产品ID
	 * @return 产品
	 * @throws Exception
	 */
	GameWebBean queryGameById(String gameId) throws Exception;

	/**
	 * 根据合作方查询产品
	 * 
	 * @param cpId
	 *            合作方ID
	 * @return 产品集合
	 * @throws Exception
	 */
	List<Game> queryAllGame(String cpId) throws Exception;

	/**
	 * 查询产品数量
	 * 
	 * @param search
	 *            查询条件
	 * @return 数量
	 * @throws Exception
	 */
	int queryGameCount(GameWebBean search) throws Exception;

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
	List<GameWebBean> queryGameList(GameWebBean search, Page page)
			throws Exception;

	/**
	 * 添加产品
	 * 
	 * @param game
	 *            产品
	 * @throws Exception
	 */
	void addGame(Game game) throws Exception;

	/**
	 * 修改产品
	 * 
	 * @param game
	 *            产品
	 * @throws Exception
	 */
	void updateGame(Game game) throws Exception;

	/**
	 * 删除产品
	 * 
	 * @param gameId
	 *            产品ID数组
	 * @throws Exception
	 */
	void deleteGame(String[] gameId) throws Exception;
}
