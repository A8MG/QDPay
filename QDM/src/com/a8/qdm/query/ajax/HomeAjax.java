package com.a8.qdm.query.ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.a8.qdm.config.dao.bean.User;
import com.a8.qdm.query.dao.bean.Crop;
import com.a8.qdm.query.service.OrderService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 欢迎页
 * 
 * @author lund
 *
 */
public class HomeAjax extends ActionSupport {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -3713784323252063818L;

	/**
	 * 管理员
	 */
	private static final String ADMIN = "0";

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(HomeAjax.class);

	/**
	 * 注入orderService
	 */
	private OrderService orderService;

	/**
	 * 跳转到欢迎页
	 */
	public String homeAjax() {

		// 入口日志
		log.info("---------------homeAjax start---------------");
		List<Crop> cropList = new ArrayList<Crop>();
		Set<String> name = new HashSet<String>();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		double[] prices = null;
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");

		// 计算上个月天数
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		int maxDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		log.info("前一个月天数：" + maxDate);

		try {
			if (ADMIN.equals(user.getAuthority())) {
				cropList = orderService.queryCpCrop();
				for (Crop crop : cropList) {
					name.add(crop.getCpName());
				}
				for (String cpName : name) {
					jsonObject = new JSONObject();
					jsonObject.put("name", cpName);
					prices = new double[maxDate];
					for (Crop crop : cropList) {
						if (cpName.equals(crop.getCpName())) {

							// 前一个月每天成交额
							prices[crop.getDayNo() - 1] = crop.getPrice() * 0.01;
						}
					}
					jsonObject.put("data", prices);
					jsonArray.add(jsonObject);
				}
			} else {
				String cpId = session.get("cpId").toString();
				cropList = orderService.queryGameCrop(cpId);
				for (Crop crop : cropList) {
					name.add(crop.getGameName());
				}
				for (String gameName : name) {
					jsonObject = new JSONObject();
					jsonObject.put("name", gameName);
					prices = new double[maxDate];
					for (Crop crop : cropList) {
						if (gameName.equals(crop.getGameName())) {

							// 前一个月每天成交额
							prices[crop.getDayNo() - 1] = crop.getPrice() * 0.01;
						}
					}
					jsonObject.put("data", prices);
					jsonArray.add(jsonObject);
				}
			}
			log.info("线性图数据：" + jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		} finally {
			try {
				ServletActionContext.getResponse().getWriter().print(jsonArray);
			} catch (IOException e) {
				e.printStackTrace();
				log.error(e);
			}
		}

		// 出口日志
		log.info("---------------homeAjax end---------------");

		return null;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
}
