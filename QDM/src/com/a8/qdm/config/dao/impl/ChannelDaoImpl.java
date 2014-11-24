package com.a8.qdm.config.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.a8.qdm.Page;
import com.a8.qdm.config.dao.ChannelDao;
import com.a8.qdm.config.dao.bean.Channel;

/**
 * 渠道Dao接口实现
 * 
 * @author lund
 *
 */
public class ChannelDaoImpl extends SqlSessionDaoSupport implements ChannelDao {

	/**
	 * 查询所有渠道
	 * 
	 * @return 渠道集合
	 */
	public List<Channel> queryAllChannel() throws Exception {
		return this.getSqlSession().selectList("queryAllChannel");
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
		return this.getSqlSession().selectOne("queryChannelById", channelId);
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
		return this.getSqlSession().selectOne("queryChannelCount", search);
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
		return this.getSqlSession().selectList("queryChannelList", map);
	}

	/**
	 * 添加渠道
	 * 
	 * @param channel
	 *            渠道
	 * @throws Exception
	 */
	public void addChannel(Channel channel) throws Exception {
		this.getSqlSession().insert("addChannel", channel);
	}

	/**
	 * 修改渠道
	 * 
	 * @param channel
	 *            渠道
	 * @throws Exception
	 */
	public void updateChannel(Channel channel) throws Exception {
		this.getSqlSession().update("updateChannel", channel);
	}

	/**
	 * 删除渠道
	 * 
	 * @param channelId
	 *            渠道ID数组
	 * @throws Exception
	 */
	public void deleteChannel(String[] channelId) throws Exception {
		this.getSqlSession().delete("deleteChannel", channelId);
	}

}
