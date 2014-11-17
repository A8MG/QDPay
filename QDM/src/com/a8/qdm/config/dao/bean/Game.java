package com.a8.qdm.config.dao.bean;

/**
 * 游戏
 * 
 * @author lund
 *
 */
public class Game {

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

	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", gameName=" + gameName
				+ ", chance=" + chance + "]";
	}
}
