package com.a8.qdm.query.service;

import com.a8.qdm.query.dao.bean.Active;

/**
 * 用户行为Service
 * 
 * @author lund
 *
 */
public interface ActiveService {
	
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
