package com.a8.qdm.config.service.impl;

import java.util.List;

import com.a8.qdm.Page;
import com.a8.qdm.config.dao.ActionDao;
import com.a8.qdm.config.dao.bean.Action;
import com.a8.qdm.config.service.ActionService;

/**
 * 行为Service接口实现
 * 
 * @author lund
 *
 */
public class ActionServiceImpl implements ActionService {

	/**
	 * actionDao注入
	 */
	private ActionDao actionDao;

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
		return actionDao.queryActionCount(gameId, cpId);
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
		return actionDao.queryActionList(gameId, cpId, page);
	}

	public ActionDao getActionDao() {
		return actionDao;
	}

	public void setActionDao(ActionDao actionDao) {
		this.actionDao = actionDao;
	}
}