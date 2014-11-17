package com.a8.qdm.config.dao.bean;

/**
 * 游戏与行为关联
 * 
 * @author Lund
 * 
 */
public class GameAction {

	/**
	 * 游戏ID
	 */
	private String gameId;

	/**
	 * 行为ID
	 */
	private String actionId;

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	@Override
	public String toString() {
		return "GameAction [gameId=" + gameId + ", actionId=" + actionId + "]";
	}
}
