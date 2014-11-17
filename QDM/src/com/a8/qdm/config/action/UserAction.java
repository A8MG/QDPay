package com.a8.qdm.config.action;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.a8.qdm.InitAction;
import com.a8.qdm.config.dao.bean.Cp;
import com.a8.qdm.config.dao.bean.User;
import com.a8.qdm.config.service.CpService;
import com.a8.qdm.config.service.UserService;

/**
 * 用户Action
 * 
 * @author lund
 *
 */
public class UserAction extends InitAction {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1629298809684844585L;

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(UserAction.class);

	/**
	 * 注入userService
	 */
	private UserService userService;

	/**
	 * 注入cpService
	 */
	private CpService cpService;

	/**
	 * 用户对象
	 */
	private User user;

	/**
	 * 用户集合
	 */
	private List<User> userList;

	/**
	 * 查询条件
	 */
	private String search = "";

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 用户名数组
	 */
	private String ids;

	/**
	 * 合作方ID
	 */
	private String cpId;

	/**
	 * 合作方集合
	 */
	private List<Cp> cpList;

	/**
	 * 查询用户列表
	 * 
	 * @return 用户列表页面
	 */
	public void queryImpl() throws Exception {

		// 入口日志
		log.info("---------------queryUserList start---------------");

		try {
			log.info("查询条件：" + search);
			page.setTotalCount(userService.queryUserCount(search));
			page.setPageCount();
			page.setStartIndex();
			log.info("分页：" + page);
			userList = userService.queryUserList(search, page);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------queryUserList end---------------");
	}

	/**
	 * 跳转到添加用户页面
	 * 
	 * @return 跳转到页面
	 */
	public String toAddUser() {

		// 入口日志
		log.info("---------------toAddUser start---------------");
		try {
			cpList = cpService.queryAllCp();
			log.info("合作方：" + cpList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------toAddUser end---------------");

		return SUCCESS;
	}

	/**
	 * 添加用户
	 * 
	 * @return 跳转页面
	 */
	public String addUser() {

		// 入口日志
		log.info("---------------addUser start---------------");

		try {
			if (cpId != null) {
				user.setAuthority(cpId);
			}

			userService.addUser(user);
			log.info("添加的用户：" + user);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------addUser end---------------");

		return SUCCESS;
	}

	/**
	 * 跳转到修改用户页面
	 * 
	 * @return 跳转页面
	 */
	public String toUpdateUser() {

		// 入口日志
		log.info("---------------toUpdateUser start---------------");
		try {
			user = userService.queryUserByName(username);
			log.info("准备修改的用户：" + user);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------toUpdateUser end---------------");

		return SUCCESS;
	}

	/**
	 * 修改用户
	 * 
	 * @return 跳转页面
	 */
	public String updateUser() {

		// 入口日志
		log.info("---------------updateUser start---------------");
		try {
			userService.updateUser(user);
			log.info("修改后的用户：" + user);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------updateUser end---------------");

		return SUCCESS;
	}

	/**
	 * 删除用户
	 * 
	 * @return 跳转页面
	 */
	public String deleteUser() {

		// 入口日志
		log.info("---------------deleteUser start---------------");
		try {
			userService.deleteUser(ids.split(","));
			log.info("删除的用户：" + ids);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------deleteUser end---------------");

		return SUCCESS;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public CpService getCpService() {
		return cpService;
	}

	public void setCpService(CpService cpService) {
		this.cpService = cpService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	public List<Cp> getCpList() {
		return cpList;
	}

	public void setCpList(List<Cp> cpList) {
		this.cpList = cpList;
	}
}
