package com.a8.qdm.config.dao;

import java.util.List;

import com.a8.qdm.Page;
import com.a8.qdm.config.dao.bean.Action;

/**
 * 行为Dao
 * 
 * @author Lund
 * 
 */
public interface ActionDao {

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

	/**
	 * 添加行为
	 * 
	 * @param actionId
	 *            行为ID
	 * @throws Exception
	 */
	void addAction(String actionId) throws Exception;

	/**
	 * 删除行为
	 * 
	 * @param actionId
	 *            行为ID数组
	 * @throws Exception
	 */
	void deleteAction(String[] actionId) throws Exception;

	/**
	 * 修改SDK打开次数
	 * 
	 * @param gameId
	 *            游戏ID
	 * @throws Exception
	 */
	void updateOpenTimes(String gameId) throws Exception;

	/**
	 * 修改确认支付次数
	 * 
	 * @param gameId
	 *            游戏ID
	 * @throws Exception
	 */
	void updateConfigTimes(String gameId) throws Exception;
}
