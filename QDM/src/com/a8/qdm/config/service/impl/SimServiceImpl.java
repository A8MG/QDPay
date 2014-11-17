package com.a8.qdm.config.service.impl;

import java.util.List;

import com.a8.qdm.Page;
import com.a8.qdm.config.dao.SimDao;
import com.a8.qdm.config.dao.bean.Sim;
import com.a8.qdm.config.service.SimService;

/**
 * 运营商Service实现
 * 
 * @author Lund
 * 
 */
public class SimServiceImpl implements SimService {

	/**
	 * simDao注入
	 */
	private SimDao simDao;

	/**
	 * 查询指令及端口
	 * 
	 * @param sim
	 *            查询条件
	 * @return sim
	 * @throws Exception
	 */
	public Sim querySim(Sim sim) throws Exception {
		return simDao.querySim(sim);
	}

	/**
	 * 根据ID查询指令
	 * 
	 * @param simId
	 *            运营商ID
	 * @return sim 指令
	 * @throws Exception
	 */
	public Sim querySimById(String simId) throws Exception {
		return simDao.querySimById(simId);
	}

	/**
	 * 查询指令数量
	 * 
	 * @param search
	 *            查询条件
	 * @return 数量
	 * @throws Exception
	 */
	public int querySimCount(Sim search) throws Exception {
		return simDao.querySimCount(search);
	}

	/**
	 * 查询指令列表
	 * 
	 * @param search
	 *            查询条件
	 * @param page
	 *            分页
	 * @return 指令列表
	 * @throws Exception
	 */
	public List<Sim> querySimList(Sim search, Page page) throws Exception {
		return simDao.querySimList(search, page);
	}

	/**
	 * 添加指令
	 * 
	 * @param sim
	 *            指令类
	 * @throws Exception
	 */
	public void addSim(Sim sim) throws Exception {
		simDao.addSim(sim);
	}

	/**
	 * 修改指令
	 * 
	 * @param sim
	 *            指令类
	 * @throws Exception
	 */
	public void updateSim(Sim sim) throws Exception {
		simDao.updateSim(sim);
	}

	/**
	 * 删除指令
	 * 
	 * @param simId
	 *            指令ID数组
	 * @throws Exception
	 */
	public void deleteSim(String[] simId) throws Exception {
		simDao.deleteSim(simId);
	}

	public SimDao getSimDao() {
		return simDao;
	}

	public void setSimDao(SimDao simDao) {
		this.simDao = simDao;
	}
}
