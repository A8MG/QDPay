package com.a8.qdm.query.service.impl;

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

	public ActiveDao getActiveDao() {
		return activeDao;
	}

	public void setActiveDao(ActiveDao activeDao) {
		this.activeDao = activeDao;
	}
}
