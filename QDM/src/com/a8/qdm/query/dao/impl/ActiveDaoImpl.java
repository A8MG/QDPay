package com.a8.qdm.query.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.a8.qdm.query.dao.ActiveDao;
import com.a8.qdm.query.dao.bean.Active;

/**
 * 用户行为Dao实现
 * 
 * @author lund
 *
 */
public class ActiveDaoImpl extends SqlSessionDaoSupport implements ActiveDao {

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
