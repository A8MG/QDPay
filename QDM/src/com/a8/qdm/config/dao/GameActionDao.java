package com.a8.qdm.config.dao;

import com.a8.qdm.config.dao.bean.GameAction;

/**
 * 游戏与行为Dao
 * 
 * @author lund
 *
 */
public interface GameActionDao {

	/**
	 * 添加游戏与行为关联关系
	 * 
	 * @param gameAction
	 *            游戏与行为关系类
	 * @throws Exception
	 */
	void addGameAction(GameAction gameAction) throws Exception;

	/**
	 * 删除游戏与行为关联关系
	 * 
	 * @param gameId
	 *            游戏ID数组
	 * @throws Exception
	 */
	void deleteGameAction(String[] gameId) throws Exception;
}
