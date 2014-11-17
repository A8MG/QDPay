package com.a8.qdm.config.service.impl;

import java.util.List;

import com.a8.qdm.Page;
import com.a8.qdm.config.dao.CpDao;
import com.a8.qdm.config.dao.bean.Cp;
import com.a8.qdm.config.service.CpService;

/**
 * 合作方Service实现
 * 
 * @author lund
 *
 */
public class CpServiceImpl implements CpService {

	/**
	 * 注入cpDao
	 */
	private CpDao cpDao;

	/**
	 * 查询所有合作方
	 * 
	 * @return 合作方集合
	 * @throws Exception
	 */
	public List<Cp> queryAllCp() throws Exception {
		return cpDao.queryAllCp();
	}

	/**
	 * 根据ID查询合作方
	 * 
	 * @param cpId
	 *            ID
	 * @return 合作方
	 * @throws Exception
	 */
	public Cp queryCp(String cpId) throws Exception {
		return cpDao.queryCp(cpId);
	}

	/**
	 * 查询合作方数量
	 * 
	 * @param search
	 *            查询条件
	 * @return 数量
	 * @throws Exception
	 */
	public int queryCpCount(Cp search) throws Exception {
		return cpDao.queryCpCount(search);
	}

	/**
	 * 查询合作方列表
	 * 
	 * @param search
	 *            查询条件
	 * @param page
	 *            分页
	 * @return 合作方集合
	 * @throws Exception
	 */
	public List<Cp> queryCpList(Cp search, Page page) throws Exception {
		return cpDao.queryCpList(search, page);
	}

	/**
	 * 添加合作方
	 * 
	 * @param cp
	 *            合作方
	 * @throws Exception
	 */
	public void addCp(Cp cp) throws Exception {
		cpDao.addCp(cp);
	}

	/**
	 * 修改合作方
	 * 
	 * @param cp
	 *            合作方
	 * @throws Exception
	 */
	public void updateCp(Cp cp) throws Exception {
		cpDao.updateCp(cp);
	}

	/**
	 * 删除合作方
	 * 
	 * @param cpId
	 *            ID
	 * @throws Exception
	 */
	public void deleteCp(String[] cpId) throws Exception {
		cpDao.deleteCp(cpId);
	}

	public CpDao getCpDao() {
		return cpDao;
	}

	public void setCpDao(CpDao cpDao) {
		this.cpDao = cpDao;
	}
}
