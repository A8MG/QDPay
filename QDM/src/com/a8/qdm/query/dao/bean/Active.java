package com.a8.qdm.query.dao.bean;

/**
 * 用户行为
 * 
 * @author lund
 *
 */
public class Active {

	/**
	 * 日期（天）
	 */
	private String dayTime;
	
	/**
	 * 设备ID
	 */
	private String deviceId;
	
	/**
	 * 支付意愿
	 */
	private String prepay;
	
	/**
	 * 时间
	 */
	private String modifyTime;

	public String getDayTime() {
		return dayTime;
	}

	public void setDayTime(String dayTime) {
		this.dayTime = dayTime;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getPrepay() {
		return prepay;
	}

	public void setPrepay(String prepay) {
		this.prepay = prepay;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "Active [dayTime=" + dayTime + ", deviceId=" + deviceId
				+ ", prepay=" + prepay + ", modifyTime=" + modifyTime + "]";
	}
}
