package com.a8.qdm.config.service;

import java.util.List;

import com.a8.qdm.Page;
import com.a8.qdm.config.dao.bean.User;

/**
 * 登录Service接口
 * 
 * @author Lund
 * 
 */
public interface UserService {

	/**
	 * 查询用户是否存在
	 * 
	 * @param user
	 *            用户
	 * @return User对象
	 * 
	 * @throws Exception
	 */
	User queryUser(User user) throws Exception;

	/**
	 * 根据用户名查询用户
	 * 
	 * @param username
	 *            用户名
	 * @return User对象
	 * 
	 * @throws Exception
	 */
	User queryUserByName(String username) throws Exception;

	/**
	 * 查询用户量
	 * 
	 * @param search
	 *            查询条件
	 * @return 数量
	 * @throws Exception
	 */
	int queryUserCount(String search) throws Exception;

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
	List<User> queryUserList(String username, Page page) throws Exception;

	/**
	 * 添加用户
	 * 
	 * @param user
	 *            用户
	 * @throws Exception
	 */
	void addUser(User user) throws Exception;

	/**
	 * 修改用户
	 * 
	 * @param user
	 *            用户
	 * @throws Exception
	 */
	void updateUser(User user) throws Exception;

	/**
	 * 删除用户
	 * 
	 * @param username
	 *            用户名
	 * @throws Exception
	 */
	void deleteUser(String[] username) throws Exception;
}
