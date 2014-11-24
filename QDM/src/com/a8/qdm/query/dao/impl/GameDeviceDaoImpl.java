package com.a8.qdm.query.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.a8.qdm.query.dao.GameDeviceDao;
import com.a8.qdm.query.dao.bean.GameDevice;

/**
 * 游戏与设备关联Dao实现
 * 
 * @author Lund
 * 
 */
public class GameDeviceDaoImpl extends SqlSessionDaoSupport implements
		GameDeviceDao {

	/**
	 * 添加游戏与设备关联关系
	 * 
	 * @param gameDevice
	 *            游戏与设备关联类
	 * @throws Exception
	 */
	public void addGameDevice(GameDevice gameDevice) throws Exception {
		this.getSqlSession().insert("addGameDevice", gameDevice);
	}
}
