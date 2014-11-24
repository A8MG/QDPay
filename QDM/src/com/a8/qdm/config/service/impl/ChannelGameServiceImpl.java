package com.a8.qdm.config.service.impl;

import com.a8.qdm.config.dao.ChannelGameDao;
import com.a8.qdm.config.service.ChannelGameService;

/**
 * 渠道与游戏关联Service实现
 * 
 * @author lund
 *
 */
public class ChannelGameServiceImpl implements ChannelGameService {

	/**
	 * channelGameDao接口注入
	 */
	private ChannelGameDao channelGameDao;

	/**
	 * 渠道是否绑定产品
	 * 
	 * @param channelId
	 *            渠道ID
	 * @return true:绑定|false:未绑定
	 * @throws Exception
	 */
	public boolean bindGame(String[] channelId) throws Exception {
		return channelGameDao.bindGame(channelId);
	}

	public ChannelGameDao getChannelGameDao() {
		return channelGameDao;
	}

	public void setChannelGameDao(ChannelGameDao channelGameDao) {
		this.channelGameDao = channelGameDao;
	}
}
