package com.a8.qdm.config.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.a8.qdm.Page;
import com.a8.qdm.config.action.bean.GameWebBean;
import com.a8.qdm.config.dao.GameDao;
import com.a8.qdm.config.dao.bean.Game;

/**
 * 产品Dao实现
 * 
 * @author lund
 *
 */
public class GameDaoImpl extends SqlSessionDaoSupport implements GameDao {

	/**
	 * 根据产品ID查询
	 * 
	 * @param gameId
	 *            产品ID
	 * @return 产品
	 * @throws Exception
	 */
	public GameWebBean queryGameById(String gameId) throws Exception {
		return this.getSqlSession().selectOne("queryGameById", gameId);
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
		return this.getSqlSession().selectList("queryAllGame", cpId);
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
		return this.getSqlSession().selectOne("queryGameCount", search);
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gameId", search.getGameId());
		map.put("gameName", search.getGameName());
		map.put("cpName", search.getCpName());
		map.put("startIndex", page.getStartIndex());
		map.put("pageSize", page.getPageSize());
		return this.getSqlSession().selectList("queryGameList", map);
	}

	/**
	 * 添加产品
	 * 
	 * @param game
	 *            产品
	 * @throws Exception
	 */
	public void addGame(Game game) throws Exception {
		this.getSqlSession().insert("addGame", game);
	}

	/**
	 * 修改产品
	 * 
	 * @param game
	 *            产品
	 * @throws Exception
	 */
	public void updateGame(Game game) throws Exception {
		this.getSqlSession().update("updateGame", game);
	}

	/**
	 * 删除产品
	 * 
	 * @param gameId
	 *            产品ID数组
	 * @throws Exception
	 */
	public void deleteGame(String[] gameId) throws Exception {
		this.getSqlSession().delete("deleteGame", gameId);
	}
}
