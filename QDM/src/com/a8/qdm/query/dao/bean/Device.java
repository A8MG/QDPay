package com.a8.qdm.query.dao.bean;

/**
 * 设备信息
 * 
 * @author Lund
 * 
 */
public class Device {

	/**
	 * 设备ID
	 */
	private String deviceId;

	/**
	 * 设备类型
	 */
	private String deviceType;

	/**
	 * IMEI
	 */
	private String imei;

	/**
	 * IMSI
	 */
	private String imsi;

	/**
	 * 系统版本
	 */
	private String systemVersion;

	/**
	 * SDK版本
	 */
	private String sdkVersion;

	/**
	 * 渠道ID
	 */
	private String channelId;

	/**
	 * 时间
	 */
	private String modifyTime;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	public String getSdkVersion() {
		return sdkVersion;
	}

	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "Device [deviceId=" + deviceId + ", deviceType=" + deviceType
				+ ", imei=" + imei + ", imsi=" + imsi + ", systemVersion="
				+ systemVersion + ", sdkVersion=" + sdkVersion + ", channelId="
				+ channelId + ", modifyTime=" + modifyTime + "]";
	}
}
