package com.a8.qdm.config.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.a8.qdm.Page;
import com.a8.qdm.config.dao.UserDao;
import com.a8.qdm.config.dao.bean.User;

/**
 * 登录DAO实现
 * 
 * @author Lund
 * 
 */
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

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
		return this.getSqlSession().selectOne("queryUser", user);
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
		return this.getSqlSession().selectOne("queryUserByName", username);
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
		return this.getSqlSession().selectOne("queryUserCount", search);
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("startIndex", page.getStartIndex());
		map.put("pageSize", page.getPageSize());
		return this.getSqlSession().selectList("queryUserList", map);
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 *            用户
	 * @throws Exception
	 */
	public void addUser(User user) throws Exception {
		this.getSqlSession().insert("addUser", user);
	}

	/**
	 * 修改用户
	 * 
	 * @param user
	 *            用户
	 * @throws Exception
	 */
	public void updateUser(User user) throws Exception {
		this.getSqlSession().update("updateUser", user);
	}

	/**
	 * 删除用户
	 * 
	 * @param username
	 *            用户名
	 * @throws Exception
	 */
	public void deleteUser(String[] username) throws Exception {
		this.getSqlSession().delete("deleteUser", username);
	}
}
