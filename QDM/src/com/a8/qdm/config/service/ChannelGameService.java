package com.a8.qdm.config.service;

/**
 * 渠道与游戏关联Service
 * 
 * @author Lund
 * 
 */
public interface ChannelGameService {

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
