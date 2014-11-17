package com.a8.qdm.query.service;

import com.a8.qdm.query.service.bean.DeviceServiceBean;

/**
 * 设备Service接口
 * 
 * @author Lund
 * 
 */
public interface DeviceService {

	/**
	 * 添加设备
	 * 
	 * @param deviceServiceBean
	 *            设备业务类
	 * @throws Exception
	 */
	void addDevice(DeviceServiceBean deviceServiceBean) throws Exception;
}
