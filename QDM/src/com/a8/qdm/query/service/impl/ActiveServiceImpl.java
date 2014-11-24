package com.a8.qdm.query.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.a8.qdm.query.dao.ActiveDao;
import com.a8.qdm.query.dao.bean.Active;
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

	@Transactional
	public void addLoginActive(Active active) throws Exception {
		Active count = activeDao.selectActive(active);
		if (null == count) {
			activeDao.addLoginActive(active);
		}
	}

	@Override
	public void updatePrepay(Active active) throws Exception {
		activeDao.updatePrepay(active);

	}
}
