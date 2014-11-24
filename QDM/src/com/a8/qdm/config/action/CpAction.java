package com.a8.qdm.config.action;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.a8.qdm.InitAction;
import com.a8.qdm.config.dao.bean.Cp;
import com.a8.qdm.config.service.CpService;
import com.a8.qdm.config.util.ConfigUtil;

/**
 * 合作方入口
 * 
 * @author lund
 *
 */
public class CpAction extends InitAction {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -2736002506252232670L;

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(CpAction.class);

	/**
	 * 注入cpService
	 */
	private CpService cpService;

	/**
	 * 合作方
	 */
	private Cp cp;

	/**
	 * 查询条件
	 */
	private Cp search;

	/**
	 * 合作方集合
	 */
	private List<Cp> cpList;

	/**
	 * 合作方ID
	 */
	private String cpId;

	/**
	 * 合作方ID数组
	 */
	private String ids;

	/**
	 * 查询合作方列表
	 * 
	 * @return 合作方列表页面
	 */
	public void queryImpl() throws Exception {

		// 入口日志
		log.info("---------------queryCpList start---------------");
		
		if (search == null) {
			search = new Cp();
			search.setCpId("");
			search.setCpName("");
		}
		log.info("查询条件：" + search);
		page.setTotalCount(cpService.queryCpCount(search));
		page.setPageCount();
		page.setStartIndex();
		log.info("分页：" + page);
		cpList = cpService.queryCpList(search, page);

		// 出口日志
		log.info("---------------queryCpList end---------------");
	}

	/**
	 * 添加合作方
	 * 
	 * @return 跳转到列表页面
	 */
	public String addCp() {

		// 入口日志
		log.info("---------------addCp start---------------");
		try {
			cp.setKeyt(ConfigUtil.makeKeyt(30));
			log.info("合作方信息：" + cp);
			cpService.addCp(cp);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------addCp end---------------");

		return SUCCESS;
	}

	/**
	 * 跳转合作方修改页面
	 * 
	 * @return 修改页面
	 */
	public String toUpdateCp() {

		// 入口日志
		log.info("---------------toUpdateCp start---------------");

		try {
			cp = cpService.queryCp(cpId);
			log.info("准备修改的合作方：" + cp);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------toUpdateCp end---------------");

		return SUCCESS;
	}

	/**
	 * 修改合作方
	 * 
	 * @return
	 */
	public String updateCp() {

		// 入口日志
		log.info("---------------updateCp start---------------");
		try {
			cpService.updateCp(cp);
			log.info("修改后合作方信息：" + cp);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------updateCp end---------------");

		return SUCCESS;
	}

	/**
	 * 删除合作方
	 * 
	 * @return 跳转列表页面
	 */
	public String deleteCp() {

		// 入口日志
		log.info("---------------deleteCp start---------------");

		try {
			cpService.deleteCp(ids.split(","));
			log.info("删除的合作方：" + ids);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------deleteCp end---------------");

		return SUCCESS;
	}

	public CpService getCpService() {
		return cpService;
	}

	public void setCpService(CpService cpService) {
		this.cpService = cpService;
	}

	public Cp getCp() {
		return cp;
	}

	public void setCp(Cp cp) {
		this.cp = cp;
	}

	public Cp getSearch() {
		return search;
	}

	public void setSearch(Cp search) {
		this.search = search;
	}

	public List<Cp> getCpList() {
		return cpList;
	}

	public void setCpList(List<Cp> cpList) {
		this.cpList = cpList;
	}

	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
