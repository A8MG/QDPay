package com.a8.qdm.config.action;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.a8.qdm.InitAction;
import com.a8.qdm.config.dao.bean.Pay;
import com.a8.qdm.config.service.PayService;

/**
 * 支付方式Action
 * 
 * @author lund
 *
 */
public class PayAction extends InitAction {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -2028004662697915727L;

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(PayAction.class);

	/**
	 * 注入payService
	 */
	private PayService payService;

	/**
	 * 支付方式类
	 */
	private Pay pay;

	/**
	 * 查询条件
	 */
	private Pay search;

	/**
	 * 支付列表
	 */
	private List<Pay> payList;

	/**
	 * 支付ID
	 */
	private String payId;

	/**
	 * 支付ID数组
	 */
	private String ids;

	/**
	 * 查询支付列表
	 * 
	 * @return 支付列表页面
	 */
	public void queryImpl() throws Exception {

		// 入口日志
		log.info("---------------queryPayList start---------------");
		
		if (search == null) {
			search = new Pay();
			search.setPayId("");
			search.setPayName("");
		}
		log.info("查询条件：" + search);
		page.setTotalCount(payService.queryPayCount(search));
		page.setPageCount();
		page.setStartIndex();
		log.info("分页：" + page);
		payList = payService.queryPayList(search, page);

		// 出口日志
		log.info("---------------queryPayList end---------------");
	}

	/**
	 * 添加支付类型
	 * 
	 * @return 跳转到列表页面
	 */
	public String addPay() {

		// 入口日志
		log.info("---------------addPay start---------------");
		try {
			payService.addPay(pay);
			log.info("添加的支付方式：" + pay);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------addPay end---------------");

		return SUCCESS;
	}

	/**
	 * 跳转到支付修改页面
	 * 
	 * @return 支付修改页面
	 */
	public String toUpdatePay() {

		// 入口日志
		log.info("---------------toUpdatePay start---------------");
		try {
			pay = payService.queryPayById(payId);
			log.info("准备修改的支付方式：" + pay);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------toUpdatePay end---------------");

		return SUCCESS;
	}

	/**
	 * 修改支付方式
	 * 
	 * @return 列表页面
	 */
	public String updatePay() {

		// 入口日志
		log.info("---------------updatePay start---------------");
		try {
			payService.updatePay(pay);
			log.info("修改后的支付方式：" + pay);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------updatePay end---------------");

		return SUCCESS;
	}

	/**
	 * 删除支付方式
	 * 
	 * @return 列表页面
	 */
	public String deletePay() {

		// 入口日志
		log.info("---------------deletePay start---------------");
		try {
			payService.deletePay(ids.split(","));
			log.info("删除的支付方式：" + ids);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------deletePay end---------------");

		return SUCCESS;
	}

	public PayService getPayService() {
		return payService;
	}

	public void setPayService(PayService payService) {
		this.payService = payService;
	}

	public Pay getPay() {
		return pay;
	}

	public void setPay(Pay pay) {
		this.pay = pay;
	}

	public Pay getSearch() {
		return search;
	}

	public void setSearch(Pay search) {
		this.search = search;
	}

	public List<Pay> getPayList() {
		return payList;
	}

	public void setPayList(List<Pay> payList) {
		this.payList = payList;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
