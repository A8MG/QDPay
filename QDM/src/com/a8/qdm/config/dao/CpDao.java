package com.a8.qdm.config.dao;

import java.util.List;

import com.a8.qdm.Page;
import com.a8.qdm.config.dao.bean.Cp;

/**
 * 合作方Dao
 * 
 * @author lund
 *
 */
public interface CpDao {

	/**
	 * 查询所有合作方
	 * 
	 * @return 合作方集合
	 * @throws Exception
	 */
	List<Cp> queryAllCp() throws Exception;

	/**
	 * 根据ID查询合作方
	 * 
	 * @param cpId
	 *            ID
	 * @return 合作方
	 * @throws Exception
	 */
	Cp queryCp(String cpId) throws Exception;

	/**
	 * 查询合作方数量
	 * 
	 * @param search
	 *            查询条件
	 * @return 数量
	 * @throws Exception
	 */
	int queryCpCount(Cp search) throws Exception;

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
	List<Cp> queryCpList(Cp search, Page page) throws Exception;

	/**
	 * 添加合作方
	 * 
	 * @param cp
	 *            合作方
	 * @throws Exception
	 */
	void addCp(Cp cp) throws Exception;

	/**
	 * 修改合作方
	 * 
	 * @param cp
	 *            合作方
	 * @throws Exception
	 */
	void updateCp(Cp cp) throws Exception;

	/**
	 * 删除合作方
	 * 
	 * @param cpId
	 *            ID
	 * @throws Exception
	 */
	void deleteCp(String[] cpId) throws Exception;
}
