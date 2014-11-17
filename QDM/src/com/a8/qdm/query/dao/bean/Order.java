package com.a8.qdm.query.dao.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 订单类
 * 
 * @author Lund
 * 
 */
public class Order {

	/**
	 * 订单号
	 */
	private String orderNo;

	/**
	 * 道具信息
	 */
	private String prop;

	/**
	 * 价格
	 */
	private String price;

	/**
	 * 支付状态
	 */
	private String state;

	/**
	 * 短信发送状态
	 */
	private String sms;

	/**
	 * cp回复状态
	 */
	private String reply;

	/**
	 * 游戏ID
	 */
	private String gameId;

	/**
	 * 用户手机号
	 */
	private String usernumber;

	/**
	 * mo与mr标识ID
	 */
	private String linkid;

	/**
	 * 时间
	 */
	private String modifyTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.format(new Date());

	/**
	 * 开始时间
	 */
	private String startTime;

	/**
	 * 结束时间
	 */
	private String endTime;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getUsernumber() {
		return usernumber;
	}

	public void setUsernumber(String usernumber) {
		this.usernumber = usernumber;
	}

	public String getLinkid() {
		return linkid;
	}

	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Order [orderNo=" + orderNo + ", prop=" + prop + ", price="
				+ price + ", state=" + state + ", sms=" + sms + ", reply="
				+ reply + ", gameId=" + gameId + ", usernumber=" + usernumber
				+ ", linkid=" + linkid + ", modifyTime=" + modifyTime
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
}
