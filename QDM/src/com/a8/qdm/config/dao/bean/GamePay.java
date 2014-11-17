package com.a8.qdm.config.dao.bean;

/**
 * 游戏与支付关联
 * 
 * @author Lund
 * 
 */
public class GamePay {

	/**
	 * 游戏ID
	 */
	private String gameId;

	/**
	 * 支付ID
	 */
	private String payId;

	/**
	 * 顺序
	 */
	private String sortNo;

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public String getSortNo() {
		return sortNo;
	}

	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}

	@Override
	public String toString() {
		return "GamePay [gameId=" + gameId + ", payId=" + payId + ", sortNo="
				+ sortNo + "]";
	}
}
