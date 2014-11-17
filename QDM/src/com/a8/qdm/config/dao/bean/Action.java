package com.a8.qdm.config.dao.bean;

/**
 * 行为类
 * 
 * @author Lund
 * 
 */
public class Action {

	/**
	 * 产品名称
	 */
	private String gameName;

	/**
	 * 打开SDK次数
	 */
	private String openTimes;

	/**
	 * 确认支付次数
	 */
	private String confirmTimes;

	/**
	 * 交易率
	 */
	private String payRate;

	/**
	 * 时间
	 */
	private String modifyTime;

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getOpenTimes() {
		return openTimes;
	}

	public void setOpenTimes(String openTimes) {
		this.openTimes = openTimes;
	}

	public String getConfirmTimes() {
		return confirmTimes;
	}

	public void setConfirmTimes(String confirmTimes) {
		this.confirmTimes = confirmTimes;
	}

	public String getPayRate() {
		return payRate;
	}

	public void setPayRate(String payRate) {
		this.payRate = payRate;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String toString() {
		return gameName + "|" + openTimes + "|" + confirmTimes + "|" + payRate
				+ "|" + modifyTime;
	}
}
