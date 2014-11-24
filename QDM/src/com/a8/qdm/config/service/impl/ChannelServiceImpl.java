package com.a8.qdm.config.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.a8.qdm.Page;
import com.a8.qdm.config.dao.ChannelDao;
import com.a8.qdm.config.dao.bean.Channel;
import com.a8.qdm.config.service.ChannelService;

/**
 * 渠道Service接口实现
 * 
 * @author lund
 *
 */
public class ChannelServiceImpl implements ChannelService {

	/**
	 * channelDao接口注入
	 */
	private ChannelDao channelDao;

	/**
	 * 查询所有渠道
	 * 
	 * @return 渠道集合
	 */
	public List<Channel> queryAllChannel() throws Exception {
		return channelDao.queryAllChannel();
	}

	/**
	 * 根据ID查询渠道
	 * 
	 * @param channelId
	 *            渠道ID
	 * @return 渠道
	 * @throws Exception
	 */
	public Channel queryChannelById(String channelId) throws Exception {
		return channelDao.queryChannelById(channelId);
	}

	/**
	 * 根据查询条件查询渠道数量
	 * 
	 * @param search
	 *            查询条件
	 * @return 渠道数量
	 * @throws Exception
	 */
	public int queryChannelCount(Channel search) throws Exception {
		return channelDao.queryChannelCount(search);
	}

	/**
	 * 根据查询条件分页查询渠道
	 * 
	 * @param search
	 *            查询条件
	 * @param page
	 *            分页
	 * @return 渠道集合
	 * @throws Exception
	 */
	public List<Channel> queryChannelList(Channel search, Page page)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("channelId", search.getChannelId());
		map.put("channelName", search.getChannelName());
		map.put("startIndex", page.getStartIndex());
		map.put("pageSize", page.getPageSize());
		return channelDao.queryChannelList(search, page);
	}

	/**
	 * 添加渠道
	 * 
	 * @param channel
	 *            渠道
	 * @throws Exception
	 */
	public void addChannel(Channel channel) throws Exception {
		channelDao.addChannel(channel);
	}

	/**
	 * 修改渠道
	 * 
	 * @param channel
	 *            渠道
	 * @throws Exception
	 */
	public void updateChannel(Channel channel) throws Exception {
		channelDao.updateChannel(channel);
	}

	/**
	 * 删除渠道
	 * 
	 * @param channelId
	 *            渠道ID数组
	 * @throws Exception
	 */
	public void deleteChannel(String[] channelId) throws Exception {
		channelDao.deleteChannel(channelId);
	}

	public ChannelDao getChannelDao() {
		return channelDao;
	}

	public void setChannelDao(ChannelDao channelDao) {
		this.channelDao = channelDao;
	}
}
