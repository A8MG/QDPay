package com.a8.qdm.config.action.bean;

/**
 * 产品业务对象
 * 
 * @author lund
 *
 */
public class GameWebBean {

	/**
	 * 游戏ID
	 */
	private String gameId;

	/**
	 * 游戏名称
	 */
	private String gameName;

	/**
	 * 通知概率
	 */
	private String chance;

	/**
	 * 合作方名称
	 */
	private String cpName;

	/**
	 * 支付名称
	 */
	private String payName;

	/**
	 * 时间
	 */
	private String modifyTime;

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getChance() {
		return chance;
	}

	public void setChance(String chance) {
		this.chance = chance;
	}

	public String getCpName() {
		return cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName;
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
		return "GameWebBean [gameId=" + gameId + ", gameName=" + gameName
				+ ", chance=" + chance + ", cpName=" + cpName + ", payName="
				+ payName + ", modifyTime=" + modifyTime + "]";
	}
}
