package com.a8.qdm.query.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.a8.qdm.Page;
import com.a8.qdm.config.dao.bean.Cp;
import com.a8.qdm.query.action.bean.OrderWebBean;
import com.a8.qdm.query.dao.OrderDao;
import com.a8.qdm.query.dao.bean.Crop;
import com.a8.qdm.query.dao.bean.Order;

/**
 * 订单Dao实现
 * 
 * @author Lund
 * 
 */
public class OrderDaoImpl extends SqlSessionDaoSupport implements OrderDao {

	/**
	 * 根据usernumber和linkid查询订单
	 * 
	 * @param order
	 *            查询条件
	 * @return 订单信息
	 * @throws Exception
	 */
	public Order queryOrderBySim(Order order) throws Exception {
		return this.getSqlSession().selectOne("queryOrderBySim", order);
	}

	/**
	 * 根据订单号查询订单
	 * 
	 * @param orderNo
	 *            查询条件
	 * @return 订单信息
	 * @throws Exception
	 */
	public Order queryOrderByNo(String orderNo) throws Exception {
		return this.getSqlSession().selectOne("queryOrderByNo", orderNo);
	}

	/**
	 * 查询游戏Url
	 * 
	 * @param orderNo
	 *            订单号
	 * @return Cp
	 * @throws Exception
	 */
	public Cp queryCpByNo(String orderNo) throws Exception {
		return this.getSqlSession().selectOne("queryCpByNo", orderNo);
	}

	/**
	 * 查询合作方前一个月交易额
	 * 
	 * @return 各合作方交易额的集合
	 * @throws Exception
	 */
	public List<Crop> queryCpCrop() throws Exception {
		return this.getSqlSession().selectList("queryCpCrop");
	}

	/**
	 * 查询产品前一个月交易额
	 * 
	 * @param cpId
	 *            合作方ID
	 * @return 各产品交易额的集合
	 * @throws Exception
	 */
	public List<Crop> queryGameCrop(String cpId) throws Exception {
		return this.getSqlSession().selectList("queryGameCrop", cpId);
	}

	/**
	 * 查询订单数量
	 * 
	 * @param cpId
	 *            合作方
	 * @param search
	 *            订单查询条件
	 * @return 数量
	 * @throws Exception
	 */
	public int queryOrderCount(String cpId, Order search) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cpId", cpId);
		map.put("orderNo", search.getOrderNo());
		map.put("gameId", search.getGameId());
		map.put("startTime", search.getStartTime());
		map.put("endTime", search.getEndTime());
		return this.getSqlSession().selectOne("queryOrderCount", map);
	}

	/**
	 * 查询订单列表
	 * 
	 * @param cpId
	 *            合作方
	 * @param search
	 *            订单查询条件
	 * @param page
	 *            分页对象
	 * @return 列表集合
	 * @throws Exception
	 */
	public List<OrderWebBean> queryOrderList(String cpId, Order search,
			Page page) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cpId", cpId);
		map.put("orderNo", search.getOrderNo());
		map.put("gameId", search.getGameId());
		map.put("startTime", search.getStartTime());
		map.put("endTime", search.getEndTime());
		map.put("startIndex", page.getStartIndex());
		map.put("pageSize", page.getPageSize());
		return this.getSqlSession().selectList("queryOrderList", map);
	}

	/**
	 * 添加订单
	 * 
	 * @param order
	 *            订单信息
	 * @throws Exception
	 */
	public void addOrder(Order order) throws Exception {
		this.getSqlSession().insert("addOrder", order);
	}

	/**
	 * 修改短信发送状态
	 * 
	 * @param orderNo
	 *            订单号
	 * @throws Exception
	 */
	public void updateSms(String orderNo) throws Exception {
		this.getSqlSession().update("updateSms", orderNo);
	}

	/**
	 * 修改付费状态
	 * 
	 * @param order
	 *            订单
	 * @throws Exception
	 */
	public void updateState(Order order) throws Exception {
		this.getSqlSession().update("updateState", order);
	}

	/**
	 * 修改CP回复状态
	 * 
	 * @param order
	 *            订单
	 * @throws Exception
	 */
	public void updateReply(Order order) throws Exception {
		this.getSqlSession().update("updateReply", order);
	}

	/**
	 * 删除订单
	 * 
	 * @param orderNo
	 *            订单数组
	 * @throws Exception
	 */
	public void deleteOrder(String[] orderNo) throws Exception {
		this.getSqlSession().delete("deleteOrder", orderNo);
	}
}
