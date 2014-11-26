package com.a8.qdm.config.service;

import java.util.List;

/**
 * 渠道与游戏关联Service
 * 
 * @author Lund
 * 
 */
public interface ChannelGameService {

	/**
	 * 查询渠道ID集合
	 * 
	 * @param gameId
	 *            游戏ID
	 * @return
	 * @throws Exception
	 */
	List<String> queryChannelId(String gameId) throws Exception;

	/**
	 * 渠道是否绑定产品
	 * 
	 * @param channelId
	 *            渠道ID
	 * @return true:绑定|false:未绑定
	 * @throws Exception
	 */
	boolean bindGame(String[] channelId) throws Exception;
}
