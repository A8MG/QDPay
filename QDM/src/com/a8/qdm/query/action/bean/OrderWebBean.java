package com.a8.qdm.query.action.bean;

/**
 * 订单WebBean
 * 
 * @author lund
 *
 */
public class OrderWebBean {

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
	 * 产品名称
	 */
	private String gameName;

	/**
	 * 支付方式
	 */
	private String payName;

	/**
	 * 时间
	 */
	private String modifyTime;

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

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
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
		return "OrderWebBean [orderNo=" + orderNo + ", prop=" + prop
				+ ", price=" + price + ", state=" + state + ", sms=" + sms
				+ ", reply=" + reply + ", gameName=" + gameName + ", payName="
				+ payName + ", modifyTime=" + modifyTime + "]";
	}
}
