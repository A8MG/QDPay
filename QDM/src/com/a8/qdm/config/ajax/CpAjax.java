package com.a8.qdm.config.ajax;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.a8.qdm.config.dao.bean.Cp;
import com.a8.qdm.config.service.CpGameService;
import com.a8.qdm.config.service.CpService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 合作方Ajax
 * 
 * @author lund
 *
 */
public class CpAjax extends ActionSupport {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -8877602235819801382L;

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(CpAjax.class);

	/**
	 * 合作方ID
	 */
	private String ids;

	/**
	 * 需要校验的值
	 */
	private String value;

	/**
	 * 注入cpGameService
	 */
	private CpGameService cpGameService;

	/**
	 * 注入cpService
	 */
	private CpService cpService;

	/**
	 * 查询合作方是否绑定产品
	 * 
	 * @return
	 */
	public String cpBindGame() {

		// 入口日志
		log.info("---------------cpBindGame start---------------");
		boolean isBind = false;

		try {
			log.info("合作方：" + ids);
			isBind = cpGameService.bindGame(ids.split(","));
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
		log.info("---------------cpBindGame end---------------");

		return null;
	}

	/**
	 * 校验合作方ID
	 * 
	 * @return
	 */
	public String checkCpId() {

		// 入口日志
		log.info("---------------checkCpId start---------------");
		boolean isAvailable = false;

		try {
			if (!"".equals(value)) {
				log.info("校验的合作方ID：" + value);
				Cp cp = cpService.queryCp(value);
				if (cp == null) {
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
		log.info("---------------checkCpId end---------------");

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

	public CpGameService getCpGameService() {
		return cpGameService;
	}

	public void setCpGameService(CpGameService cpGameService) {
		this.cpGameService = cpGameService;
	}

	public CpService getCpService() {
		return cpService;
	}

	public void setCpService(CpService cpService) {
		this.cpService = cpService;
	}
}
