package com.a8.qdm.config.action;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.a8.qdm.InitAction;
import com.a8.qdm.config.dao.bean.Sim;
import com.a8.qdm.config.service.SimService;

/**
 * 指令Action
 * 
 * @author lund
 *
 */
public class SimAction extends InitAction {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 7519450549770827708L;

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(SimAction.class);

	/**
	 * 注入simService
	 */
	private SimService simService;

	/**
	 * 指令类
	 */
	private Sim sim;

	/**
	 * 查询条件
	 */
	private Sim search;

	/**
	 * 指令集合
	 */
	private List<Sim> simList;

	/**
	 * 运营商ID
	 */
	private String simId;

	/**
	 * 运营商ID数组
	 */
	private String ids;

	/**
	 * 查询指令列表
	 * 
	 * @return 指令列表页面
	 */
	public void queryImpl() throws Exception {

		// 入口日志
		log.info("---------------querySimList start---------------");
		try {
			if (search == null) {
				search = new Sim();
				search.setSimType("");
				search.setCommand("");
				search.setPrice("");
			}
			log.info("查询条件：" + search);
			page.setTotalCount(simService.querySimCount(search));
			page.setPageCount();
			page.setStartIndex();
			simList = simService.querySimList(search, page);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------querySimList end---------------");
	}

	/**
	 * 添加指令
	 * 
	 * @return 跳转到指令列表页面
	 */
	public String addSim() {

		// 入口日志
		log.info("---------------addSim start---------------");
		try {
			simService.addSim(sim);
			log.info("添加的指令：" + sim);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------addSim end---------------");

		return SUCCESS;
	}

	/**
	 * 跳转到修改指令页面
	 * 
	 * @return 修改指令页面
	 */
	public String toUpdateSim() {

		// 入口日志
		log.info("---------------toUpdateSim start---------------");
		try {
			sim = simService.querySimById(simId);
			log.info("准备修改的指令：" + sim);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------toUpdateSim end---------------");

		return SUCCESS;
	}

	/**
	 * 修改指令
	 * 
	 * @return 列表页面
	 */
	public String updateSim() {

		// 入口日志
		log.info("---------------updateSim start---------------");
		try {
			simService.updateSim(sim);
			log.info("修改后的指令：" + sim);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------updateSim end---------------");

		return SUCCESS;
	}

	/**
	 * 删除指令
	 * 
	 * @return 列表页面
	 */
	public String deleteSim() {

		// 入口日志
		log.info("---------------deleteSim start---------------");
		try {
			simService.deleteSim(ids.split(","));
			log.info("删除的指令：" + ids);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------deleteSim end---------------");

		return SUCCESS;
	}

	public SimService getSimService() {
		return simService;
	}

	public void setSimService(SimService simService) {
		this.simService = simService;
	}

	public Sim getSim() {
		return sim;
	}

	public void setSim(Sim sim) {
		this.sim = sim;
	}

	public Sim getSearch() {
		return search;
	}

	public void setSearch(Sim search) {
		this.search = search;
	}

	public List<Sim> getSimList() {
		return simList;
	}

	public void setSimList(List<Sim> simList) {
		this.simList = simList;
	}

	public String getSimId() {
		return simId;
	}

	public void setSimId(String simId) {
		this.simId = simId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
