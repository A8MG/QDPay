package com.a8.qdm.query.service.bean;

import com.a8.qdm.query.dao.bean.Device;
import com.a8.qdm.query.dao.bean.GameDevice;

/**
 * 设备业务类
 * 
 * @author Lund
 * 
 */
public class DeviceServiceBean {

	/**
	 * 设备
	 */
	private Device device;

	/**
	 * 游戏与设备关联
	 */
	private GameDevice gameDevice;

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public GameDevice getGameDevice() {
		return gameDevice;
	}

	public void setGameDevice(GameDevice gameDevice) {
		this.gameDevice = gameDevice;
	}

	public String toString() {
		return device + "|" + gameDevice;
	}
}
