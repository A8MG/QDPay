package com.a8.qdm.config.dao;

import java.util.List;

import com.a8.qdm.config.dao.bean.GamePay;

/**
 * 游戏与支付关联Dao
 * 
 * @author Lund
 * 
 */
public interface GamePayDao {

	/**
	 * 查询支付列表ID
	 * 
	 * @param gameId
	 *            游戏ID
	 * @return payIdList
	 * @throws Exception
	 */
	List<String> queryPayId(String gameId) throws Exception;

	/**
	 * 查询支付列表
	 * 
	 * @param gameId
	 *            游戏ID
	 * @return payNameList
	 * @throws Exception
	 */
	List<String> queryPayName(String gameId) throws Exception;

	/**
	 * 添加产品与支付关联关系
	 * 
	 * @param gamePayList
	 *            关联类集合
	 * @throws Exception
	 */
	void addGamePay(List<GamePay> gamePayList) throws Exception;

	/**
	 * 删除产品与支付关联关系
	 * 
	 * @param gameId
	 *            产品ID数组
	 * @throws Exception
	 */
	void deleteGamePay(String[] gameId) throws Exception;

	/**
	 * 支付方式是否绑定产品
	 * 
	 * @param payId
	 *            支付方式ID
	 * @return true:绑定|false:未绑定
	 * @throws Exception
	 */
	boolean bindGame(String[] payId) throws Exception;
}
