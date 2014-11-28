package com.a8.qdm.config.ajax;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.a8.qdm.config.action.bean.GameWebBean;
import com.a8.qdm.config.dao.bean.Game;
import com.a8.qdm.config.dao.bean.Pay;
import com.a8.qdm.config.service.GameService;
import com.a8.qdm.config.service.PayService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 产品Ajax
 * 
 * @author lund
 *
 */
public class GameAjax extends ActionSupport {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -7629502365986806547L;

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(GameAjax.class);

	/**
	 * 首选支付方式ID
	 */
	private String firstPayId;

	/**
	 * 其它支付方式中已选支付
	 */
	private String choosedPay;

	/**
	 * 需要校验的值
	 */
	private String value;

	/**
	 * 渠道ID
	 */
	private String channelId;

	/**
	 * 注入payService
	 */
	private PayService payService;

	/**
	 * 注入gameService
	 */
	private GameService gameService;

	/**
	 * 动态加载其它支付方式
	 * 
	 * @return 无需跳转页面
	 */
	public String ajaxPay() {

		// 入口日志
		log.info("---------------ajaxPay start---------------");
		StringBuilder otherPay = new StringBuilder();
		try {
			// 支付方式集合
			List<Pay> payList = payService.queryAllPay();
			log.info("支付方式：" + payList);
			log.info("首选支付方式：" + firstPayId);
			if (payList != null && !payList.isEmpty()) {

				// 去掉首选项支付方式
				for (Pay pay : payList) {
					if (firstPayId != null && !firstPayId.isEmpty()
							&& !firstPayId.equals(pay.getPayId())) {

						// 拼接其它支付方式页面
						otherPay.append(
								"<input name='choosedPayList' type='checkbox' value='")
								.append(pay.getPayId()).append("' ");
						if (choosedPay != null) {
							for (String payId : choosedPay.split(",")) {
								if (payId.equals(pay.getPayId())) {
									otherPay.append("checked='checked'");
								}
							}
						}
						otherPay.append(">").append(pay.getPayName())
								.append("</input>");
					}
				}
			}

			// 返回给ajax
			ServletActionContext.getResponse().getWriter().print(otherPay);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------ajaxPay end---------------");

		return null;
	}

	/**
	 * 校验产品ID
	 * 
	 * @return
	 */
	public String checkGameId() {

		// 入口日志
		log.info("---------------checkGameId start---------------");
		boolean isAvailable = false;

		try {
			if (!"".equals(value)) {
				log.info("校验的产品ID：" + value);
				GameWebBean gameWebBean = gameService.queryGameById(value);
				if (gameWebBean == null) {
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
		log.info("---------------checkGameId end---------------");

		return null;
	}

	public String ajaxGameList() {

		// 入口日志
		log.info("---------------ajaxGameList start---------------");
		StringBuilder gameSelect = new StringBuilder();

		try {
			// 根据渠道ID查询产品集合
			log.info("渠道：" + channelId);
			List<Game> gameList = gameService.queryGameListById(channelId);
			log.info("产品：" + gameList);

			// 拼接下拉列表
			if (gameList != null && !gameList.isEmpty()) {
				gameSelect.append("<select id='gameId' name='gameId'>");
				for (Game game : gameList) {
					gameSelect.append("<option value='")
							.append(game.getGameId()).append("'>")
							.append(game.getGameName()).append("</option>")
							.append("</select>");
				}
			}

			// 返回给ajax
			ServletActionContext.getResponse().getWriter().print(gameSelect);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------ajaxGameList end---------------");

		return null;
	}

	public String getFirstPayId() {
		return firstPayId;
	}

	public void setFirstPayId(String firstPayId) {
		this.firstPayId = firstPayId;
	}

	public String getChoosedPay() {
		return choosedPay;
	}

	public void setChoosedPay(String choosedPay) {
		this.choosedPay = choosedPay;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public PayService getPayService() {
		return payService;
	}

	public void setPayService(PayService payService) {
		this.payService = payService;
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}
}
