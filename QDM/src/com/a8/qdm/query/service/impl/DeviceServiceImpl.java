package com.a8.qdm.query.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.a8.qdm.query.dao.DeviceDao;
import com.a8.qdm.query.dao.GameDeviceDao;
import com.a8.qdm.query.service.DeviceService;
import com.a8.qdm.query.service.bean.DeviceServiceBean;

/**
 * 设备Service实现
 * 
 * @author Lund
 * 
 */
public class DeviceServiceImpl implements DeviceService {

	/**
	 * deviceDao接口注入
	 */
	private DeviceDao deviceDao;

	/**
	 * gameDeviceDao接口注入
	 */
	private GameDeviceDao gameDeviceDao;

	/**
	 * 添加设备
	 * 
	 * @param deviceServiceBean
	 *            设备业务类
	 * @throws Exception
	 */
	@Transactional
	public void addDevice(DeviceServiceBean deviceServiceBean) throws Exception {

		// 查询设备是否存在
		int count = deviceDao.queryDevice(deviceServiceBean.getDevice()
				.getDeviceId());

		// 不存在添加
		if (count == 0) {
			deviceDao.addDevice(deviceServiceBean.getDevice());
			gameDeviceDao.addGameDevice(deviceServiceBean.getGameDevice());
		}
	}

	public DeviceDao getDeviceDao() {
		return deviceDao;
	}

	public void setDeviceDao(DeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}

	public GameDeviceDao getGameDeviceDao() {
		return gameDeviceDao;
	}

	public void setGameDeviceDao(GameDeviceDao gameDeviceDao) {
		this.gameDeviceDao = gameDeviceDao;
	}
}
