package com.a8.qdm.config.service;

import java.util.List;

import com.a8.qdm.Page;
import com.a8.qdm.config.dao.bean.Action;

/**
 * 行为Service接口
 * 
 * @author lund
 *
 */
public interface ActionService {

	/**
	 * 查询行为总数
	 * 
	 * @param gameId
	 *            产品ID
	 * @param cpId
	 *            合作方ID
	 * @return 总数
	 * @throws Exception
	 */
	int queryActionCount(String gameId, String cpId) throws Exception;
	
	/**
	 * 查询行为列表
	 * 
	 * @param gameId
	 *            产品ID
	 * @param cpId
	 *            合作方ID
	 * @param page
	 *            分页
	 * @return list
	 * @throws Exception
	 */
	List<Action> queryActionList(String gameId, String cpId, Page page)
			throws Exception;
}