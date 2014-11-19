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
	 * 支付意愿金额
	 */
	private String price;

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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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
				+ ", price=" + price + ", modifyTime=" + modifyTime + "]";
	}
}
