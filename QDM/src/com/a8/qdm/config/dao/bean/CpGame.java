package com.a8.qdm.config.dao.bean;

/**
 * CP与游戏关联类
 * 
 * @author Lund
 * 
 */
public class CpGame {

	/**
	 * cpId
	 */
	private String cpId;

	/**
	 * gameId
	 */
	private String gameId;

	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	@Override
	public String toString() {
		return "CpGame [cpId=" + cpId + ", gameId=" + gameId + "]";
	}
}
