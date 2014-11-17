package com.a8.qdm.config.dao;

import com.a8.qdm.config.dao.bean.CpGame;

/**
 * CP与游戏关联Dao
 * 
 * @author Lund
 * 
 */
public interface CpGameDao {

	/**
	 * 添加CP与游戏关联关系
	 * 
	 * @param cpGame
	 *            CP与游戏关联类
	 * @throws Exception
	 */
	void addCpGame(CpGame cpGame) throws Exception;

	/**
	 * 删除CP与游戏关联关系
	 * 
	 * @param gameId
	 *            游戏ID数组
	 * @throws Exception
	 */
	void deleteCpGame(String[] gameId) throws Exception;

	/**
	 * 合作方是否绑定产品
	 * 
	 * @param cpId
	 *            合作方ID
	 * @return true:绑定|false:未绑定
	 * @throws Exception
	 */
	boolean bindGame(String[] cpId) throws Exception;
}
