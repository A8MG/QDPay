package com.a8.qdm.config.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.a8.qdm.config.dao.CpGameDao;
import com.a8.qdm.config.dao.bean.CpGame;

/**
 * CP与游戏关联Dao实现
 * 
 * @author Lund
 * 
 */
public class CpGameDaoImpl extends SqlSessionDaoSupport implements CpGameDao {

	/**
	 * 添加CP与游戏关联关系
	 * 
	 * @param cpGame
	 *            CP与游戏关联类
	 * @throws Exception
	 */
	public void addCpGame(CpGame cpGame) throws Exception {
		this.getSqlSession().insert("addCpGame", cpGame);
	}

	/**
	 * 删除CP与游戏关联关系
	 * 
	 * @param gameId
	 *            游戏ID数组
	 * @throws Exception
	 */
	public void deleteCpGame(String[] gameId) throws Exception {
		this.getSqlSession().delete("deleteCpGame", gameId);
	}

	/**
	 * 合作方是否绑定产品
	 * 
	 * @param cpId
	 *            合作方ID
	 * @return true:绑定|false:未绑定
	 * @throws Exception
	 */
	public boolean bindGame(String[] cpId) throws Exception {
		List<CpGame> cpGameList = this.getSqlSession().selectList(
				"queryCpGameList", cpId);
		if (cpGameList != null && !cpGameList.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
