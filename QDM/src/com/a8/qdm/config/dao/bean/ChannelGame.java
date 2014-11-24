package com.a8.qdm.config.dao.bean;

/**
 * 渠道与游戏关系
 * 
 * @author lund
 *
 */
public class ChannelGame {

	/**
	 * 渠道ID
	 */
	private String channelId;

	/**
	 * 游戏ID
	 */
	private String gameId;

	/**
	 * 时间
	 */
	private String modifyTime;

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "ChannelGame [channelId=" + channelId + ", gameId=" + gameId
				+ ", modifyTime=" + modifyTime + "]";
	}
}
