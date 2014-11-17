package com.a8.qdm.query.dao;

import java.util.List;

import com.a8.qdm.Page;
import com.a8.qdm.config.dao.bean.Cp;
import com.a8.qdm.query.action.bean.OrderWebBean;
import com.a8.qdm.query.dao.bean.Crop;
import com.a8.qdm.query.dao.bean.Order;

/**
 * 订单Dao
 * 
 * @author Lund
 * 
 */
public interface OrderDao {

	/**
	 * 根据usernumber和linkid查询订单
	 * 
	 * @param order
	 *            查询条件
	 * @return 订单信息
	 * @throws Exception
	 */
	Order queryOrderBySim(Order order) throws Exception;

	/**
	 * 根据订单号查询订单
	 * 
	 * @param orderNo
	 *            查询条件
	 * @return 订单信息
	 * @throws Exception
	 */
	Order queryOrderByNo(String orderNo) throws Exception;

	/**
	 * 查询游戏Url
	 * 
	 * @param orderNo
	 *            订单号
	 * @return Cp
	 * @throws Exception
	 */
	Cp queryCpByNo(String orderNo) throws Exception;

	/**
	 * 查询合作方前一个月交易额
	 * 
	 * @return 各合作方交易额的集合
	 * @throws Exception
	 */
	List<Crop> queryCpCrop() throws Exception;

	/**
	 * 查询产品前一个月交易额
	 * 
	 * @param cpId
	 *            合作方ID
	 * @return 各产品交易额的集合
	 * @throws Exception
	 */
	List<Crop> queryGameCrop(String cpId) throws Exception;

	/**
	 * 查询订单数量
	 * 
	 * @param cpId
	 *            合作方
	 * @param search
	 *            订单查询条件
	 * 
	 * @return 数量
	 * @throws Exception
	 */
	int queryOrderCount(String cpId, Order search) throws Exception;

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
	List<OrderWebBean> queryOrderList(String cpId, Order search, Page page)
			throws Exception;

	/**
	 * 添加订单
	 * 
	 * @param order
	 *            订单信息
	 * @throws Exception
	 */
	void addOrder(Order order) throws Exception;

	/**
	 * 修改短信发送状态
	 * 
	 * @param orderNo
	 *            订单号
	 * @throws Exception
	 */
	void updateSms(String orderNo) throws Exception;

	/**
	 * 修改付费状态
	 * 
	 * @param order
	 *            订单
	 * @throws Exception
	 */
	void updateState(Order order) throws Exception;

	/**
	 * 修改CP回复状态
	 * 
	 * @param order
	 *            订单
	 * @throws Exception
	 */
	void updateReply(Order order) throws Exception;

	/**
	 * 删除订单
	 * 
	 * @param orderNo
	 *            订单数组
	 * @throws Exception
	 */
	void deleteOrder(String[] orderNo) throws Exception;
}
