package com.a8.qdm.config.dao.bean;

/**
 * 电信运营商信息
 * 
 * @author Lund
 * 
 */
public class Sim {

	/**
	 * 运营商ID
	 */
	private String simId;

	/**
	 * 运营商类型
	 */
	private String simType;

	/**
	 * 指令
	 */
	private String command;

	/**
	 * 短信服务端
	 */
	private String simServer;

	/**
	 * 价格
	 */
	private String price;

	/**
	 * 适用类型（用于区分同一运营商的指令）
	 */
	private String applyType;

	/**
	 * 时间
	 */
	private String modifyTime;

	public String getSimId() {
		return simId;
	}

	public void setSimId(String simId) {
		this.simId = simId;
	}

	public String getSimType() {
		return simType;
	}

	public void setSimType(String simType) {
		this.simType = simType;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getSimServer() {
		return simServer;
	}

	public void setSimServer(String simServer) {
		this.simServer = simServer;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "Sim [simId=" + simId + ", simType=" + simType + ", command="
				+ command + ", simServer=" + simServer + ", price=" + price
				+ ", applyType=" + applyType + ", modifyTime=" + modifyTime
				+ "]";
	}
}
