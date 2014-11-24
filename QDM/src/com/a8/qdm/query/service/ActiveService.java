package com.a8.qdm.query.service;

import java.util.Map;

import com.a8.qdm.query.action.bean.ActiveWebBean;
import com.a8.qdm.query.dao.bean.Active;

/**
 * 用户行为Service
 * 
 * @author lund
 *
 */
public interface ActiveService {

	/**
	 * 查询渠道用户数据
	 * 
	 * @param map
	 *            查询条件
	 * @return
	 * @throws Exception
	 */
	ActiveWebBean queryActiveNo(Map<String, String> map) throws Exception;
	
	/**
	 * 添加用户登录行为
	 * @param active
	 * @throws Exception
	 */
	void addLoginActive(Active active)throws Exception;
	
	/**
	 * 修改用户付费意愿行为
	 * @param active
	 * @throws Exception
	 */
	void updatePrepay(Active active)throws Exception;
}
