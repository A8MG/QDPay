package com.a8.qdm.config.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.a8.qdm.config.dao.GameActionDao;
import com.a8.qdm.config.dao.bean.GameAction;

/**
 * 游戏与行为Dao实现
 * 
 * @author lund
 *
 */
public class GameActionDaoImpl extends SqlSessionDaoSupport implements
		GameActionDao {

	/**
	 * 添加游戏与行为关联关系
	 * 
	 * @param gameAction
	 *            游戏与行为关系类
	 * @throws Exception
	 */
	public void addGameAction(GameAction gameAction) throws Exception {
		this.getSqlSession().insert("addGameAction", gameAction);
	}

	/**
	 * 删除游戏与行为关联关系
	 * 
	 * @param gameId
	 *            游戏ID数组
	 * @throws Exception
	 */
	public void deleteGameAction(String[] gameId) throws Exception {
		this.getSqlSession().delete("deleteGameAction", gameId);
	}
}
