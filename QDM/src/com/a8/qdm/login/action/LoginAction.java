package com.a8.qdm.login.action;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.a8.qdm.config.dao.bean.User;
import com.a8.qdm.config.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 4251246916979624082L;

	/**
	 * 管理员
	 */
	private static final String ADMIN = "0";

	/**
	 * 合作方
	 */
	private static final String CP = "1";

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(LoginAction.class);

	/**
	 * 注入Service
	 */
	private UserService userService;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 登录
	 * 
	 * @return 跳转页面
	 */
	public String login() {

		// 入口日志
		log.info("---------------login start---------------");
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = new User();
		boolean isExist = false;

		// 查询用户是否存在
		try {
			log.info("用户" + username + "准备登陆");
			user.setUsername(username);
			user.setPassword(password);
			User userInfo = userService.queryUser(user);
			if (userInfo != null) {
				log.info("用户名和密码正确");
				String authority = userInfo.getAuthority();
				String cpId = "";
				String channelId = "";
				if (ADMIN.equals(authority)) {
					log.info("用户" + username + "的权限为管理员");
				} else {
					String authType = authority.split("[|]")[0];
					String authId = authority.split("[|]")[1];
					if (CP.equals(authType)) {
						log.info("用户" + username + "的权限为合作方");
						cpId = authId;
					} else {
						log.info("用户" + username + "的权限为渠道方");
						channelId = authId;
					}
				}
				session.put("user", userInfo);
				session.put("cpId", cpId);
				session.put("channelId", channelId);

				isExist = true;
			} else {
				log.info("用户名或密码有误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		} finally {
			try {
				ServletActionContext.getResponse().getWriter().print(isExist);
			} catch (IOException e) {
				e.printStackTrace();
				log.error(e);
			}
		}

		// 出口日志
		log.info("---------------login end---------------");
		return null;
	}

	/**
	 * 退出
	 * 
	 * @return 跳转页面
	 */
	public String logout() {

		// 入口日志
		log.info("---------------logout start---------------");
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		if (user != null) {
			log.info("用户" + user.getUsername() + "已退出");
			session.clear();
		}

		// 出口日志
		log.info("---------------logout end---------------");
		return SUCCESS;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

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
}