package com.a8.qdm.config.service.impl;

import java.util.List;

import com.a8.qdm.config.dao.GamePayDao;
import com.a8.qdm.config.service.GamePayService;

/**
 * 游戏与支付关联Service实现
 * 
 * @author Lund
 * 
 */
public class GamePayServiceImpl implements GamePayService {

	/**
	 * gamePayDao接口注入
	 */
	private GamePayDao gamePayDao;

	/**
	 * 查询支付列表
	 * 
	 * @param gameId
	 *            游戏ID
	 * @return payList
	 * @throws Exception
	 */
	public List<String> queryPayId(String gameId) throws Exception {

		// 查询支付列表
		List<String> list = gamePayDao.queryPayId(gameId);

		return list;
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
		return gamePayDao.bindGame(payId);
	}

	public GamePayDao getGamePayDao() {
		return gamePayDao;
	}

	public void setGamePayDao(GamePayDao gamePayDao) {
		this.gamePayDao = gamePayDao;
	}
}