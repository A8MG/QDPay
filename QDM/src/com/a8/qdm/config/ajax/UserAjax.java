package com.a8.qdm.config.ajax;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.a8.qdm.config.dao.bean.User;
import com.a8.qdm.config.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 合作方Ajax
 * 
 * @author lund
 *
 */
public class UserAjax extends ActionSupport {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -4130841091277827264L;

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(UserAjax.class);

	/**
	 * 需要校验的值
	 */
	private String value;

	/**
	 * 注入userService
	 */
	private UserService userService;

	/**
	 * 校验用户名
	 * 
	 * @return
	 */
	public String checkUserName() {

		// 入口日志
		log.info("---------------checkUserName start---------------");
		boolean isAvailable = false;

		try {
			if (!"".equals(value)) {
				log.info("校验的用户名：" + value);
				User user = userService.queryUserByName(value);
				if (user == null) {
					isAvailable = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		} finally {
			try {
				ServletActionContext.getResponse().getWriter()
						.print(isAvailable);
			} catch (IOException e) {
				e.printStackTrace();
				log.error(e);
			}
		}

		// 出口日志
		log.info("---------------checkUserName end---------------");

		return null;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
