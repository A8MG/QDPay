package com.a8.qdm.query.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.a8.qdm.query.dao.DeviceDao;
import com.a8.qdm.query.dao.bean.Device;

/**
 * 设备Dao实现
 * 
 * @author Lund
 * 
 */
public class DeviceDaoImpl extends SqlSessionDaoSupport implements DeviceDao {

	/**
	 * 添加设备
	 * 
	 * @param device
	 *            设备
	 * @throws Exception
	 */
	public void addDevice(Device device) throws Exception {
		this.getSqlSession().insert("addDevice", device);
	}

	/**
	 * 查询设备是否存在
	 * 
	 * @param deviceId
	 *            设备ID
	 * @throws Exception
	 */
	public int queryDevice(String deviceId) throws Exception {
		return this.getSqlSession().selectOne("queryDevice", deviceId);
	}
}
