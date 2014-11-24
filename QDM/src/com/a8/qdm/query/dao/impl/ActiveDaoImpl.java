package com.a8.qdm.query.dao.impl;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.a8.qdm.query.action.bean.ActiveWebBean;
import com.a8.qdm.query.dao.ActiveDao;

/**
 * 用户行为Dao实现
 * 
 * @author lund
 *
 */
public class ActiveDaoImpl extends SqlSessionDaoSupport implements ActiveDao {

	/**
	 * 查询渠道用户数据
	 * 
	 * @param map
	 *            查询条件
	 * @return
	 * @throws Exception
	 */
	public ActiveWebBean queryActiveNo(Map<String, String> map)
			throws Exception {
		return this.getSqlSession().selectOne("queryActiveNo", map);
	}
}
