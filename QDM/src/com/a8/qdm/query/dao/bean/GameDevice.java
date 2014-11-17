package com.a8.qdm.query.dao.bean;

/**
 * 游戏与设备关联类
 * 
 * @author Lund
 * 
 */
public class GameDevice {

	/**
	 * 游戏ID
	 */
	private String gameId;

	/**
	 * 设备ID
	 */
	private String deviceId;

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public String toString() {
		return "GameDevice [gameId=" + gameId + ", deviceId=" + deviceId + "]";
	}
}
