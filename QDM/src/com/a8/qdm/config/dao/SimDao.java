package com.a8.qdm.config.dao;

import java.util.List;

import com.a8.qdm.Page;
import com.a8.qdm.config.dao.bean.Sim;

/**
 * 运营商Dao
 * 
 * @author Lund
 * 
 */
public interface SimDao {

	/**
	 * 查询指令及端口
	 * 
	 * @param sim
	 *            查询条件
	 * @return sim
	 * @throws Exception
	 */
	Sim querySim(Sim sim) throws Exception;

	/**
	 * 根据ID查询指令
	 * 
	 * @param simId
	 *            运营商ID
	 * @return sim 指令
	 * @throws Exception
	 */
	Sim querySimById(String simId) throws Exception;

	/**
	 * 查询指令数量
	 * 
	 * @param search
	 *            查询条件
	 * @return 数量
	 * @throws Exception
	 */
	int querySimCount(Sim search) throws Exception;

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
	List<Sim> querySimList(Sim search, Page page) throws Exception;

	/**
	 * 添加指令
	 * 
	 * @param sim
	 *            指令类
	 * @throws Exception
	 */
	void addSim(Sim sim) throws Exception;

	/**
	 * 修改指令
	 * 
	 * @param sim
	 *            指令类
	 * @throws Exception
	 */
	void updateSim(Sim sim) throws Exception;

	/**
	 * 删除指令
	 * 
	 * @param simId
	 *            指令ID数组
	 * @throws Exception
	 */
	void deleteSim(String[] simId) throws Exception;
}
