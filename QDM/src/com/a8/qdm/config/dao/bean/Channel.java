package com.a8.qdm.config.dao.bean;

/**
 * 渠道
 * 
 * @author lund
 *
 */
public class Channel {

	/**
	 * 渠道ID
	 */
	private String channelId;

	/**
	 * 渠道名称
	 */
	private String channelName;

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

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "Channel [channelId=" + channelId + ", channelName="
				+ channelName + ", modifyTime=" + modifyTime + "]";
	}
}
