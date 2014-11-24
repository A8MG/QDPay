package com.a8.qdm.config.dao;

import com.a8.qdm.config.dao.bean.ChannelGame;

/**
 * 渠道与游戏关联Dao
 * 
 * @author Lund
 * 
 */
public interface ChannelGameDao {

	/**
	 * 添加渠道与游戏关联关系
	 * 
	 * @param channelGame
	 *            渠道与游戏关联类
	 * @throws Exception
	 */
	void addChannelGame(ChannelGame channelGame) throws Exception;

	/**
	 * 删除渠道与游戏关联关系
	 * 
	 * @param gameId
	 *            游戏ID数组
	 * @throws Exception
	 */
	void deleteChannelGame(String[] gameId) throws Exception;

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
