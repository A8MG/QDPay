package com.a8.qdm.query.dao;

import com.a8.qdm.query.dao.bean.GameDevice;

/**
 * 游戏与设备关联Dao
 * 
 * @author Lund
 * 
 */
public interface GameDeviceDao {

	/**
	 * 添加游戏与设备关联关系
	 * 
	 * @param gameDevice
	 *            游戏与设备关联类
	 * @throws Exception
	 */
	void addGameDevice(GameDevice gameDevice) throws Exception;
}
