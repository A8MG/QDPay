package com.a8.qdm.config.service;

import java.util.List;

/**
 * 游戏与支付关联Service
 * 
 * @author Lund
 * 
 */
public interface GamePayService {

	/**
	 * 查询支付列表
	 * 
	 * @param gameId
	 *            游戏ID
	 * @return payList
	 * @throws Exception
	 */
	List<String> queryPayId(String gameId) throws Exception;

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
