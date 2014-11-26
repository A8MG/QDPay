package com.a8.qdm.config.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.a8.qdm.config.dao.GamePayDao;
import com.a8.qdm.config.dao.bean.GamePay;

/**
 * 游戏与支付关联Dao实现
 * 
 * @author Lund
 * 
 */
public class GamePayDaoImpl extends SqlSessionDaoSupport implements GamePayDao {

	/**
	 * 查询支付列表
	 * 
	 * @param gameId
	 *            游戏ID
	 * @return payList
	 * @throws Exception
	 */
	public List<String> queryPayId(String gameId) throws Exception {
		return this.getSqlSession().selectList("queryPayId", gameId);
	}

	/**
	 * 添加游戏与支付关联关系
	 * 
	 * @param gamePayList
	 *            关联类集合
	 * @throws Exception
	 */
	public void addGamePay(List<GamePay> gamePayList) throws Exception {
		this.getSqlSession().insert("addGamePay", gamePayList);
	}

	/**
	 * 删除产品与支付关联关系
	 * 
	 * @param gameId
	 *            产品ID数组
	 * @throws Exception
	 */
	public void deleteGamePay(String[] gameId) throws Exception {
		this.getSqlSession().delete("deleteGamePay", gameId);
	}

	/**
	 * 支付方式是否绑定产品
	 * 
	 * @param payId
	 *            支付方式ID
	 * @return true:绑定|false:未绑定
	 * @throws Exception
	 */
	public boolean bindGame(String[] payId) throws Exception {
		List<GamePay> gamePayList = this.getSqlSession().selectList(
				"queryGamePayList", payId);
		if (gamePayList != null && !gamePayList.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
