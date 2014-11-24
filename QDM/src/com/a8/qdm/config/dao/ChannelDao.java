package com.a8.qdm.config.dao;

import java.util.List;

import com.a8.qdm.Page;
import com.a8.qdm.config.dao.bean.Channel;

/**
 * 渠道Dao接口
 * 
 * @author lund
 *
 */
public interface ChannelDao {

	/**
	 * 查询所有渠道
	 * 
	 * @return 渠道集合
	 */
	List<Channel> queryAllChannel() throws Exception;

	/**
	 * 根据ID查询渠道
	 * 
	 * @param channelId
	 *            渠道ID
	 * @return 渠道
	 * @throws Exception
	 */
	Channel queryChannelById(String channelId) throws Exception;

	/**
	 * 根据查询条件查询渠道数量
	 * 
	 * @param search
	 *            查询条件
	 * @return 渠道数量
	 * @throws Exception
	 */
	int queryChannelCount(Channel search) throws Exception;

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
	List<Channel> queryChannelList(Channel search, Page page) throws Exception;

	/**
	 * 添加渠道
	 * 
	 * @param channel
	 *            渠道
	 * @throws Exception
	 */
	void addChannel(Channel channel) throws Exception;

	/**
	 * 修改渠道
	 * 
	 * @param channel
	 *            渠道
	 * @throws Exception
	 */
	void updateChannel(Channel channel) throws Exception;

	/**
	 * 删除渠道
	 * 
	 * @param channelId
	 *            渠道ID数组
	 * @throws Exception
	 */
	void deleteChannel(String[] channelId) throws Exception;
}
