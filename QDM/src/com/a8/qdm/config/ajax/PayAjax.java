package com.a8.qdm.config.ajax;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.a8.qdm.config.dao.bean.Pay;
import com.a8.qdm.config.service.GamePayService;
import com.a8.qdm.config.service.PayService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 支付方式Ajax
 * 
 * @author lund
 *
 */
public class PayAjax extends ActionSupport {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -8356795579913952112L;

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(PayAjax.class);

	/**
	 * 合作方ID
	 */
	private String ids;

	/**
	 * 需要校验的值
	 */
	private String value;

	/**
	 * 注入payService
	 */
	private PayService payService;

	/**
	 * 注入gamePayService
	 */
	private GamePayService gamePayService;

	/**
	 * 查询支付方式是否绑定产品
	 * 
	 * @return
	 */
	public String payBindGame() {

		// 入口日志
		log.info("---------------payBindGame start---------------");
		boolean isBind = false;

		try {
			log.info("支付方式：" + ids);
			isBind = gamePayService.bindGame(ids.split(","));
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		} finally {
			try {
				ServletActionContext.getResponse().getWriter().print(isBind);
			} catch (IOException e) {
				e.printStackTrace();
				log.error(e);
			}
		}

		// 出口日志
		log.info("---------------payBindGame end---------------");

		return null;
	}

	/**
	 * 校验支付方式ID
	 * 
	 * @return
	 */
	public String checkPayId() {

		// 入口日志
		log.info("---------------checkPayId start---------------");
		boolean isAvailable = false;

		try {
			if (!"".equals(value)) {
				log.info("校验的支付方式ID：" + value);
				Pay pay = payService.queryPayById(value);
				if (pay == null) {
					isAvailable = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		} finally {
			try {
				ServletActionContext.getResponse().getWriter()
						.print(isAvailable);
			} catch (IOException e) {
				e.printStackTrace();
				log.error(e);
			}
		}

		// 出口日志
		log.info("---------------checkPayId end---------------");

		return null;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public PayService getPayService() {
		return payService;
	}

	public void setPayService(PayService payService) {
		this.payService = payService;
	}

	public GamePayService getGamePayService() {
		return gamePayService;
	}

	public void setGamePayService(GamePayService gamePayService) {
		this.gamePayService = gamePayService;
	}
}
