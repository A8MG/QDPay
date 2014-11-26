package com.a8.qdm.config.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.a8.qdm.config.dao.ChannelGameDao;
import com.a8.qdm.config.dao.bean.ChannelGame;

/**
 * 渠道与游戏关联Dao实现
 * 
 * @author lund
 *
 */
public class ChannelGameDaoImpl extends SqlSessionDaoSupport implements
		ChannelGameDao {

	/**
	 * 查询渠道ID集合
	 * 
	 * @param gameId
	 *            游戏ID
	 * @return
	 * @throws Exception
	 */
	public List<String> queryChannelId(String gameId) throws Exception {
		return this.getSqlSession().selectList("queryChannelId", gameId);
	}

	/**
	 * 添加渠道与游戏关联关系
	 * 
	 * @param channelGameList
	 *            渠道与游戏关联类集合
	 * @throws Exception
	 */
	public void addChannelGame(List<ChannelGame> channelGameList)
			throws Exception {
		this.getSqlSession().insert("addChannelGame", channelGameList);
	}

	/**
	 * 删除渠道与游戏关联关系
	 * 
	 * @param gameId
	 *            游戏ID数组
	 * @throws Exception
	 */
	public void deleteChannelGame(String[] gameId) throws Exception {
		this.getSqlSession().delete("deleteChannelGame", gameId);
	}

	/**
	 * 渠道是否绑定产品
	 * 
	 * @param channelId
	 *            渠道ID
	 * @return true:绑定|false:未绑定
	 * @throws Exception
	 */
	public boolean bindGame(String[] channelId) throws Exception {
		List<ChannelGame> channelGameList = this.getSqlSession().selectList(
				"queryChannelGameList", channelId);
		if (channelGameList != null && !channelGameList.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}
