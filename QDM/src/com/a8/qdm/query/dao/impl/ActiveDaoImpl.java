package com.a8.qdm.query.dao.impl;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.a8.qdm.query.action.bean.ActiveWebBean;
import com.a8.qdm.query.dao.ActiveDao;
import com.a8.qdm.query.dao.bean.Active;

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
	
	@Override
	public void addLoginActive(Active active) throws Exception {
		this.getSqlSession().insert("addLoginActive",active);

	}

	@Override
	public void updatePrepay(Active active) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlSession().update("updatePrepay",active);
	}

	@Override
	public Active selectActive(Active active) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("selectActive",active);
	}
}
