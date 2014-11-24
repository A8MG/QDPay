package com.a8.qdm.config.ajax;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.a8.qdm.config.dao.bean.Channel;
import com.a8.qdm.config.service.ChannelGameService;
import com.a8.qdm.config.service.ChannelService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 渠道Ajax
 * 
 * @author lund
 *
 */
public class ChannelAjax extends ActionSupport {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 302437366351885637L;

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(ChannelAjax.class);

	/**
	 * 渠道ID
	 */
	private String ids;

	/**
	 * 需要校验的值
	 */
	private String value;

	/**
	 * 注入channelGameService
	 */
	private ChannelGameService channelGameService;

	/**
	 * 注入channelService
	 */
	private ChannelService channelService;

	/**
	 * 查询渠道是否绑定产品
	 * 
	 * @return
	 */
	public String channelBindGame() {

		// 入口日志
		log.info("---------------channelBindGame start---------------");
		boolean isBind = false;

		try {
			log.info("渠道：" + ids);
			isBind = channelGameService.bindGame(ids.split(","));
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
		log.info("---------------channelBindGame end---------------");

		return null;
	}

	/**
	 * 校验渠道ID
	 * 
	 * @return
	 */
	public String checkChannelId() {

		// 入口日志
		log.info("---------------checkChannelId start---------------");
		boolean isAvailable = false;

		try {
			if (!"".equals(value)) {
				log.info("校验的渠道ID：" + value);
				Channel channel = channelService.queryChannelById(value);
				if (channel == null) {
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
		log.info("---------------checkChannelId end---------------");

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

	public ChannelGameService getChannelGameService() {
		return channelGameService;
	}

	public void setChannelGameService(ChannelGameService channelGameService) {
		this.channelGameService = channelGameService;
	}

	public ChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(ChannelService channelService) {
		this.channelService = channelService;
	}
}
