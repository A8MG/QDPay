package com.a8.qdm.config.service.impl;

import java.util.List;

import com.a8.qdm.Page;
import com.a8.qdm.config.dao.UserDao;
import com.a8.qdm.config.dao.bean.User;
import com.a8.qdm.config.service.UserService;

/**
 * 登录Service实现
 * 
 * @author Lund
 * 
 */
public class UserServiceImpl implements UserService {

	/**
	 * 注入Dao
	 */
	private UserDao userDao;

	/**
	 * 查询用户是否存在
	 * 
	 * @param user
	 *            用户
	 * @return User对象
	 * 
	 * @throws Exception
	 */
	public User queryUser(User user) throws Exception {
		return userDao.queryUser(user);
	}

	/**
	 * 根据用户名查询用户
	 * 
	 * @param username
	 *            用户名
	 * @return User对象
	 * 
	 * @throws Exception
	 */
	public User queryUserByName(String username) throws Exception {
		return userDao.queryUserByName(username);
	}

	/**
	 * 查询用户量
	 * 
	 * @param search
	 *            查询条件
	 * @return 数量
	 * @throws Exception
	 */
	public int queryUserCount(String search) throws Exception {
		return userDao.queryUserCount(search);
	}

	/**
	 * 查询用户列表
	 * 
	 * @param username
	 *            查询条件
	 * @param page
	 *            分页
	 * @return 用户列表页面
	 * @throws Exception
	 */
	public List<User> queryUserList(String username, Page page)
			throws Exception {
		return userDao.queryUserList(username, page);
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 *            用户
	 * @throws Exception
	 */
	public void addUser(User user) throws Exception {
		userDao.addUser(user);
	}

	/**
	 * 修改用户
	 * 
	 * @param user
	 *            用户
	 * @throws Exception
	 */
	public void updateUser(User user) throws Exception {
		userDao.updateUser(user);
	}

	/**
	 * 删除用户
	 * 
	 * @param username
	 *            用户名
	 * @throws Exception
	 */
	public void deleteUser(String[] username) throws Exception {
		userDao.deleteUser(username);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
