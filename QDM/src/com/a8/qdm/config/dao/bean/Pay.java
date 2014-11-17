package com.a8.qdm.config.dao.bean;

/**
 * 支付类
 * 
 * @author Lund
 * 
 */
public class Pay {

	/**
	 * 支付ID
	 */
	private String payId;

	/**
	 * 支付名称
	 */
	private String payName;

	/**
	 * 时间
	 */
	private String modifyTime;

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "Pay [payId=" + payId + ", payName=" + payName + ", modifyTime="
				+ modifyTime + "]";
	}
}
