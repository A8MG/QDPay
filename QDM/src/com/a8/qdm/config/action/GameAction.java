package com.a8.qdm.config.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.a8.qdm.InitAction;
import com.a8.qdm.config.action.bean.GameWebBean;
import com.a8.qdm.config.dao.bean.Cp;
import com.a8.qdm.config.dao.bean.CpGame;
import com.a8.qdm.config.dao.bean.Game;
import com.a8.qdm.config.dao.bean.GamePay;
import com.a8.qdm.config.dao.bean.Pay;
import com.a8.qdm.config.service.CpService;
import com.a8.qdm.config.service.GamePayService;
import com.a8.qdm.config.service.GameService;
import com.a8.qdm.config.service.PayService;

/**
 * 产品入口
 * 
 * @author lund
 *
 */
public class GameAction extends InitAction {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -2115634031786964008L;

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(GameAction.class);

	/**
	 * 首选支付方式排序标识：1
	 */
	private static String FIRST_PAY = "1";

	/**
	 * 其它支付方式排序标识：0
	 */
	private static String OTHER_PAY = "0";

	/**
	 * 注入gameService
	 */
	private GameService gameService;

	/**
	 * 注入cpService
	 */
	private CpService cpService;

	/**
	 * 注入payService
	 */
	private PayService payService;

	/**
	 * 注入gamePayService
	 */
	private GamePayService gamePayService;

	/**
	 * 产品类
	 */
	private Game game;

	/**
	 * 合作方ID
	 */
	private String cpId;

	/**
	 * 合作方集合
	 */
	private List<Cp> cpList;

	/**
	 * 支付方式集合
	 */
	private List<Pay> payList;

	/**
	 * 首选支付方式ID
	 */
	private String firstPayId;

	/**
	 * 已选择关联的支付方式
	 */
	private List<String> choosedPayList;

	/**
	 * 产品集合
	 */
	private List<GameWebBean> gameWebBeanList;

	/**
	 * 查询条件
	 */
	private GameWebBean search;

	/**
	 * 产品ID
	 */
	private String gameId;

	/**
	 * 产品ID数组
	 */
	private String ids;

	/**
	 * 产品展示
	 */
	private GameWebBean gameWebBean;

	/**
	 * 查询产品列表
	 * 
	 * @return 产品列表页面
	 */
	public void queryImpl() throws Exception {

		// 入口日志
		log.info("---------------queryGameList start---------------");

		if (search == null) {
			search = new GameWebBean();
			search.setGameId("");
			search.setGameName("");
			search.setCpName("");
		}
		log.info("查询条件：" + search);
		page.setTotalCount(gameService.queryGameCount(search));
		page.setPageCount();
		page.setStartIndex();
		log.info("分页：" + page);
		gameWebBeanList = gameService.queryGameList(search, page);

		// 出口日志
		log.info("---------------queryGameList end---------------");
	}

	/**
	 * 准备添加产品
	 * 
	 * @return 添加产品页面
	 */
	public String toAddGame() {

		// 入口日志
		log.info("---------------toAddGame start---------------");

		try {
			// 查询合作方
			cpList = cpService.queryAllCp();
			log.info("合作方：" + cpList);

			// 查询支付方式
			payList = payService.queryAllPay();
			log.info("支付方式：" + payList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------toAddGame end---------------");

		return SUCCESS;
	}

	/**
	 * 添加产品
	 * 
	 * @return 跳转到产品列表页面
	 */
	public String addGame() {

		// 入口日志
		log.info("---------------addGame start---------------");
		List<GamePay> gamePayList = new ArrayList<GamePay>();

		try {
			if (cpId != null && !cpId.isEmpty()) {
				if (firstPayId != null && !firstPayId.isEmpty()) {

					// 组装关联的合作方
					CpGame cpGame = new CpGame();
					cpGame.setCpId(cpId);
					cpGame.setGameId(game.getGameId());
					log.info("关联的合作方：" + cpId);

					// 组装关联的支付方式
					GamePay firstGamePay = new GamePay();
					firstGamePay.setGameId(game.getGameId());
					firstGamePay.setPayId(firstPayId);
					firstGamePay.setSortNo(FIRST_PAY);
					gamePayList.add(firstGamePay);
					if (choosedPayList != null && !choosedPayList.isEmpty()) {
						for (String payId : choosedPayList) {
							GamePay otherGamePay = new GamePay();
							otherGamePay.setGameId(game.getGameId());
							otherGamePay.setPayId(payId);
							otherGamePay.setSortNo(OTHER_PAY);
							gamePayList.add(otherGamePay);
						}
					}
					log.info("关联的支付方式：" + gamePayList);

					// 添加产品及关联关系
					gameService.addGame(game, cpGame, gamePayList);
				} else {
					log.info("请选择关联的支付方式，如果没有选择项，请添加支付方式");
				}
			} else {
				log.info("请选择合作方，如果没有选择项，请添加合作方");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------addGame end---------------");

		return SUCCESS;
	}

	/**
	 * 跳转到产品修改页面
	 * 
	 * @return 修改页面
	 */
	public String toUpdateGame() {

		// 入口日志
		log.info("---------------toUpdateGame start---------------");
		try {
			// 查询产品及关联合作方
			gameWebBean = gameService.queryGameById(gameId);
			log.info("准备修改的产品：" + gameWebBean);

			// 查询所有支付方式
			payList = payService.queryAllPay();

			// 查询已选支付方式
			choosedPayList = gamePayService.queryPayId(gameId);
			log.info("修改产品已选支付方式：" + choosedPayList);

			// 首选支付
			firstPayId = choosedPayList.get(0);

			// 其它支付
			choosedPayList.remove(0);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------toUpdateGame end---------------");

		return SUCCESS;
	}

	/**
	 * 修改产品
	 * 
	 * @return 列表页面
	 */
	public String updateGame() {

		// 入口日志
		log.info("---------------updateGame start---------------");
		try {
			List<GamePay> gamePayList = new ArrayList<GamePay>();

			// 产品
			game.setChance(gameWebBean.getChance());

			// 首选支付
			GamePay firstGamePay = new GamePay();
			firstGamePay.setGameId(game.getGameId());
			firstGamePay.setPayId(firstPayId);
			firstGamePay.setSortNo(FIRST_PAY);
			gamePayList.add(firstGamePay);

			// 其它支付
			if (choosedPayList != null) {
				for (String payId : choosedPayList) {
					GamePay otherGamePay = new GamePay();
					otherGamePay.setGameId(game.getGameId());
					otherGamePay.setPayId(payId);
					otherGamePay.setSortNo(OTHER_PAY);
					gamePayList.add(otherGamePay);
				}
			}

			// 修改产品及支付方式
			gameService.updateGame(game, gamePayList);
			log.info("修改后的产品信息：" + game);
			log.info("修改后产品关联的支付方式：" + gamePayList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------updateGame end---------------");

		return SUCCESS;
	}

	/**
	 * 删除产品
	 * 
	 * @return 列表页面
	 */
	public String deleteGame() {

		// 入口日志
		log.info("---------------deleteGame start---------------");
		try {
			gameService.deleteGame(ids.split(","));
			log.info("删除的产品：" + ids);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------deleteGame end---------------");

		return SUCCESS;
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	public CpService getCpService() {
		return cpService;
	}

	public void setCpService(CpService cpService) {
		this.cpService = cpService;
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

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	public List<Cp> getCpList() {
		return cpList;
	}

	public void setCpList(List<Cp> cpList) {
		this.cpList = cpList;
	}

	public List<Pay> getPayList() {
		return payList;
	}

	public void setPayList(List<Pay> payList) {
		this.payList = payList;
	}

	public String getFirstPayId() {
		return firstPayId;
	}

	public void setFirstPayId(String firstPayId) {
		this.firstPayId = firstPayId;
	}

	public List<String> getChoosedPayList() {
		return choosedPayList;
	}

	public void setChoosedPayList(List<String> choosedPayList) {
		this.choosedPayList = choosedPayList;
	}

	public List<GameWebBean> getGameWebBeanList() {
		return gameWebBeanList;
	}

	public void setGameWebBeanList(List<GameWebBean> gameWebBeanList) {
		this.gameWebBeanList = gameWebBeanList;
	}

	public GameWebBean getSearch() {
		return search;
	}

	public void setSearch(GameWebBean search) {
		this.search = search;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public GameWebBean getGameWebBean() {
		return gameWebBean;
	}

	public void setGameWebBean(GameWebBean gameWebBean) {
		this.gameWebBean = gameWebBean;
	}
}
