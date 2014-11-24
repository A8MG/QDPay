package com.a8.qdm.query.action;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.a8.qdm.InitAction;
import com.a8.qdm.config.dao.bean.Game;
import com.a8.qdm.config.service.GameService;
import com.a8.qdm.query.action.bean.OrderWebBean;
import com.a8.qdm.query.dao.bean.Order;
import com.a8.qdm.query.service.OrderService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 订单Action
 * 
 * @author lund
 *
 */
public class OrderAction extends InitAction {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -7607822086757195729L;

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(OrderAction.class);

	/**
	 * 注入orderService
	 */
	private OrderService orderService;

	/**
	 * 注入gameService
	 */
	private GameService gameService;

	/**
	 * 查询条件
	 */
	private Order search;

	/**
	 * 订单列表
	 */
	private List<OrderWebBean> orderWebBeanList;

	/**
	 * 产品列表
	 */
	private List<Game> gameList;

	/**
	 * 订单数组
	 */
	private String ids;

	/**
	 * 查询订单列表
	 */
	public void queryImpl() throws Exception {

		// 入口日志
		log.info("---------------queryOrderList start---------------");
		Map<String, Object> session = ActionContext.getContext().getSession();

		String cpId = session.get("cpId").toString();
		log.info("合作方ID：" + cpId);
		gameList = gameService.queryAllGame(cpId);
		log.info("合作方" + cpId + "下的产品：" + gameList);

		if (search == null) {
			search = new Order();
			search.setOrderNo("");
			search.setGameId("");
			search.setStartTime("");
			search.setEndTime("");
		}
		log.info("查询条件：" + search);
		page.setTotalCount(orderService.queryOrderCount(cpId, search));
		page.setPageCount();
		page.setStartIndex();
		log.info("分页：" + page);
		orderWebBeanList = orderService.queryOrderList(cpId, search, page);

		// 出口日志
		log.info("---------------queryOrderList end---------------");
	}

	/**
	 * 删除订单
	 * 
	 * @return 跳转列表页面
	 */
	public String deleteOrder() {

		// 入口日志
		log.info("---------------deleteOrder start---------------");
		try {
			orderService.deleteOrder(ids.split(","));
			log.info("删除的订单：" + ids);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------deleteOrder end---------------");

		return SUCCESS;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	public Order getSearch() {
		return search;
	}

	public void setSearch(Order search) {
		this.search = search;
	}

	public List<OrderWebBean> getOrderWebBeanList() {
		return orderWebBeanList;
	}

	public void setOrderWebBeanList(List<OrderWebBean> orderWebBeanList) {
		this.orderWebBeanList = orderWebBeanList;
	}

	public List<Game> getGameList() {
		return gameList;
	}

	public void setGameList(List<Game> gameList) {
		this.gameList = gameList;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
