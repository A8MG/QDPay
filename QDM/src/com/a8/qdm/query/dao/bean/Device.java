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

	public String toString() {
		return deviceId + "|" + "deviceType" + "|" + imei + "|" + imsi + "|"
				+ systemVersion + "|" + sdkVersion;
	}
}
