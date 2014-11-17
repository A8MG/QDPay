package com.a8.qdm.config.dao.bean;

/**
 * 合作商
 * 
 * @author Lund
 * 
 */
public class Cp {

	/**
	 * 合作商ID
	 */
	private String cpId;

	/**
	 * 合作商名称
	 */
	private String cpName;

	/**
	 * 合作商URL
	 */
	private String httpUrl;

	/**
	 * 密钥
	 */
	private String keyt;

	/**
	 * 时间
	 */
	private String modifyTime;

	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	public String getCpName() {
		return cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName;
	}

	public String getHttpUrl() {
		return httpUrl;
	}

	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}

	public String getKeyt() {
		return keyt;
	}

	public void setKeyt(String keyt) {
		this.keyt = keyt;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "Cp [cpId=" + cpId + ", cpName=" + cpName + ", httpUrl="
				+ httpUrl + ", keyt=" + keyt + ", modifyTime=" + modifyTime
				+ "]";
	}
}
