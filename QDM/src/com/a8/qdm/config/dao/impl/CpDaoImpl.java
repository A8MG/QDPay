package com.a8.qdm.config.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.a8.qdm.Page;
import com.a8.qdm.config.dao.CpDao;
import com.a8.qdm.config.dao.bean.Cp;

/**
 * 合作方Dao实现
 * 
 * @author lund
 *
 */
public class CpDaoImpl extends SqlSessionDaoSupport implements CpDao {

	/**
	 * 查询所有合作方
	 * 
	 * @return 合作方集合
	 * @throws Exception
	 */
	public List<Cp> queryAllCp() throws Exception {
		return this.getSqlSession().selectList("queryAllCp");
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
		return this.getSqlSession().selectOne("queryCp", cpId);
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
		return this.getSqlSession().selectOne("queryCpCount", search);
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cpId", search.getCpId());
		map.put("cpName", search.getCpName());
		map.put("startIndex", page.getStartIndex());
		map.put("pageSize", page.getPageSize());
		return this.getSqlSession().selectList("queryCpList", map);
	}

	/**
	 * 添加合作方
	 * 
	 * @param cp
	 *            合作方
	 * @throws Exception
	 */
	public void addCp(Cp cp) throws Exception {
		this.getSqlSession().insert("addCp", cp);
	}

	/**
	 * 修改合作方
	 * 
	 * @param cp
	 *            合作方
	 * @throws Exception
	 */
	public void updateCp(Cp cp) throws Exception {
		this.getSqlSession().update("updateCp", cp);
	}

	/**
	 * 删除合作方
	 * 
	 * @param cpId
	 *            ID
	 * @throws Exception
	 */
	public void deleteCp(String[] cpId) throws Exception {
		this.getSqlSession().delete("deleteCp", cpId);
	}
}