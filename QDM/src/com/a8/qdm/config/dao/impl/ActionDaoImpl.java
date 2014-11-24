package com.a8.qdm.config.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.a8.qdm.Page;
import com.a8.qdm.config.dao.ActionDao;
import com.a8.qdm.config.dao.bean.Action;

/**
 * 行为Dao实现
 * 
 * @author Lund
 * 
 */
public class ActionDaoImpl extends SqlSessionDaoSupport implements ActionDao {

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
	public int queryActionCount(String gameId, String cpId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gameId", gameId);
		map.put("cpId", cpId);
		return this.getSqlSession().selectOne("queryActionCount", map);
	}

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
	public List<Action> queryActionList(String gameId, String cpId, Page page)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gameId", gameId);
		map.put("cpId", cpId);
		map.put("startIndex", page.getStartIndex());
		map.put("pageSize", page.getPageSize());
		return this.getSqlSession().selectList("queryActionList", map);
	}

	/**
	 * 添加行为
	 * 
	 * @param actionId
	 *            行为ID
	 * @throws Exception
	 */
	public void addAction(String actionId) throws Exception {
		this.getSqlSession().insert("addAction", actionId);
	}

	/**
	 * 删除行为
	 * 
	 * @param actionId
	 *            行为ID数组
	 * @throws Exception
	 */
	public void deleteAction(String[] actionId) throws Exception {
		this.getSqlSession().delete("deleteAction", actionId);
	}

	/**
	 * 修改SDK打开次数
	 * 
	 * @param gameId
	 *            游戏ID
	 * @throws Exception
	 */
	public void updateOpenTimes(String gameId) throws Exception {
		this.getSqlSession().insert("updateOpenTimes", gameId);
	}

	/**
	 * 修改确认支付次数
	 * 
	 * @param gameId
	 *            游戏ID
	 * @throws Exception
	 */
	public void updateConfigTimes(String gameId) throws Exception {
		this.getSqlSession().insert("updateConfigTimes", gameId);
	}

}
