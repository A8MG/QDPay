package com.a8.qdm.query.action.bean;

/**
 * 活跃用户展示类
 * 
 * @author lund
 *
 */
public class ActiveWebBean {

	/**
	 * 日新增用户数
	 */
	private String addNo;
	
	/**
	 * 日活跃用户数
	 */
	private String dayActiveNo;
	
	/**
	 * 周活跃用户数
	 */
	private String weekActiveNo;
	
	/**
	 * 付费意愿用户数
	 */
	private String prePayNo;
	
	/**
	 * 丢失用户数
	 */
	private String loseNo;

	public String getAddNo() {
		return addNo;
	}

	public void setAddNo(String addNo) {
		this.addNo = addNo;
	}

	public String getDayActiveNo() {
		return dayActiveNo;
	}

	public void setDayActiveNo(String dayActiveNo) {
		this.dayActiveNo = dayActiveNo;
	}

	public String getWeekActiveNo() {
		return weekActiveNo;
	}

	public void setWeekActiveNo(String weekActiveNo) {
		this.weekActiveNo = weekActiveNo;
	}

	public String getPrePayNo() {
		return prePayNo;
	}

	public void setPrePayNo(String prePayNo) {
		this.prePayNo = prePayNo;
	}

	public String getLoseNo() {
		return loseNo;
	}

	public void setLoseNo(String loseNo) {
		this.loseNo = loseNo;
	}

	@Override
	public String toString() {
		return "ActiveWebBean [addNo=" + addNo + ", dayActiveNo=" + dayActiveNo
				+ ", weekActiveNo=" + weekActiveNo + ", prePayNo=" + prePayNo
				+ ", loseNo=" + loseNo + "]";
	}
}
