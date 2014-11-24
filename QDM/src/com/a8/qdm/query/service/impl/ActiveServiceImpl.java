package com.a8.qdm.query.service.impl;

import java.util.Map;

import com.a8.qdm.query.action.bean.ActiveWebBean;
import com.a8.qdm.query.dao.ActiveDao;
import com.a8.qdm.query.service.ActiveService;

/**
 * 用户行为Service实现
 * 
 * @author lund
 *
 */
public class ActiveServiceImpl implements ActiveService {

	/**
	 * 注入activeDao
	 */
	private ActiveDao activeDao;

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
		return activeDao.queryActiveNo(map);
	}

	public ActiveDao getActiveDao() {
		return activeDao;
	}

	public void setActiveDao(ActiveDao activeDao) {
		this.activeDao = activeDao;
	}
}
