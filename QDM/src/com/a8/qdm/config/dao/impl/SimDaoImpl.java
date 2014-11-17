package com.a8.qdm.config.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.a8.qdm.Page;
import com.a8.qdm.config.dao.SimDao;
import com.a8.qdm.config.dao.bean.Sim;

/**
 * 运营商Dao实现
 * 
 * @author Lund
 * 
 */
public class SimDaoImpl extends SqlSessionDaoSupport implements SimDao {

	/**
	 * 查询指令及端口
	 * 
	 * @param sim
	 *            查询条件
	 * @return sim
	 * @throws Exception
	 */
	public Sim querySim(Sim sim) throws Exception {
		return this.getSqlSession().selectOne("querySim", sim);
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
		return this.getSqlSession().selectOne("querySimById", simId);
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
		return this.getSqlSession().selectOne("querySimCount", search);
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("simType", search.getSimType());
		map.put("command", search.getCommand());
		map.put("price", search.getPrice());
		map.put("startIndex", page.getStartIndex());
		map.put("pageSize", page.getPageSize());
		return this.getSqlSession().selectList("querySimList", map);
	}

	/**
	 * 添加指令
	 * 
	 * @param sim
	 *            指令类
	 * @throws Exception
	 */
	public void addSim(Sim sim) throws Exception {
		this.getSqlSession().insert("addSim", sim);
	}

	/**
	 * 修改指令
	 * 
	 * @param sim
	 *            指令类
	 * @throws Exception
	 */
	public void updateSim(Sim sim) throws Exception {
		this.getSqlSession().update("updateSim", sim);
	}

	/**
	 * 删除指令
	 * 
	 * @param simId
	 *            指令ID数组
	 * @throws Exception
	 */
	public void deleteSim(String[] simId) throws Exception {
		this.getSqlSession().delete("deleteSim", simId);
	}
}
