package com.a8.qdm.config.service.impl;

import com.a8.qdm.config.dao.CpGameDao;
import com.a8.qdm.config.service.CpGameService;

/**
 * 合作方与产品业务接口实现
 * 
 * @author lund
 *
 */
public class CpGameServiceImpl implements CpGameService {

	/**
	 * 注入cpGameDao
	 */
	private CpGameDao cpGameDao;

	/**
	 * 合作方是否绑定产品
	 * 
	 * @param cpId
	 *            合作方ID
	 * @return true:绑定|false:未绑定
	 * @throws Exception
	 */
	public boolean bindGame(String[] cpId) throws Exception {
		return cpGameDao.bindGame(cpId);
	}

	public CpGameDao getCpGameDao() {
		return cpGameDao;
	}

	public void setCpGameDao(CpGameDao cpGameDao) {
		this.cpGameDao = cpGameDao;
	}
}
