package com.a8.qdm.query.dao;

import com.a8.qdm.query.dao.bean.Device;

/**
 * 设备Dao
 * 
 * @author Lund
 * 
 */
public interface DeviceDao {

	/**
	 * 添加设备
	 * 
	 * @param device
	 *            设备
	 * @throws Exception
	 */
	void addDevice(Device device) throws Exception;

	/**
	 * 查询设备是否存在
	 * 
	 * @param deviceId
	 *            设备ID
	 * @throws Exception
	 */
	int queryDevice(String deviceId) throws Exception;
}
