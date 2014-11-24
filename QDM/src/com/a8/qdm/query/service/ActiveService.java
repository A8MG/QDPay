package com.a8.qdm.query.service;

import java.util.Map;

import com.a8.qdm.query.action.bean.ActiveWebBean;

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
}
