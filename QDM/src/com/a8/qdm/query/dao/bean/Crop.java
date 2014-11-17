package com.a8.qdm.query.dao.bean;

/**
 * 交易类
 * 
 * @author lund
 *
 */
public class Crop {

	/**
	 * 合作方名称
	 */
	private String cpName;

	/**
	 * 产品名称
	 */
	private String gameName;

	/**
	 * 金额
	 */
	private int price;

	/**
	 * 日期
	 */
	private int dayNo;

	public String getCpName() {
		return cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDayNo() {
		return dayNo;
	}

	public void setDayNo(int dayNo) {
		this.dayNo = dayNo;
	}

	/**
	 * 重写toString方法
	 */
	public String toString() {
		return cpName + "|" + gameName + "|" + price + "|" + dayNo;
	}
}
