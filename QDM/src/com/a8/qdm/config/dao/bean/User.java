package com.a8.qdm.config.dao.bean;

/**
 * 用户信息
 * 
 * @author Lund
 * 
 */
public class User {

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 权限
	 */
	private String authority;

	/**
	 * 时间
	 */
	private String modifyTime;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", authority=" + authority + ", modifyTime=" + modifyTime
				+ "]";
	}
}
