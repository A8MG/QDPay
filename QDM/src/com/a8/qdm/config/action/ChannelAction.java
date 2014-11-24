package com.a8.qdm.config.action;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.a8.qdm.InitAction;
import com.a8.qdm.config.dao.bean.Channel;
import com.a8.qdm.config.service.ChannelService;

/**
 * 渠道Action
 * 
 * @author lund
 *
 */
public class ChannelAction extends InitAction {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 7878857765802629102L;

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(ChannelAction.class);

	/**
	 * channelService接口注入
	 */
	private ChannelService channelService;

	/**
	 * 渠道
	 */
	private Channel channel;

	/**
	 * 查询条件
	 */
	private Channel search;

	/**
	 * 渠道列表
	 */
	private List<Channel> channelList;

	/**
	 * 渠道ID
	 */
	private String channelId;

	/**
	 * 渠道ID数组
	 */
	private String ids;

	/**
	 * 分页查询渠道列表
	 */
	public void queryImpl() throws Exception {

		// 入口日志
		log.info("---------------queryChannelList start---------------");

		if (search == null) {
			search = new Channel();
			search.setChannelId("");
			search.setChannelName("");
		}
		log.info("查询条件：" + search);
		page.setTotalCount(channelService.queryChannelCount(search));
		page.setPageCount();
		page.setStartIndex();
		log.info("分页：" + page);
		channelList = channelService.queryChannelList(search, page);

		// 出口日志
		log.info("---------------queryChannelList end---------------");
	}

	/**
	 * 添加渠道
	 * 
	 * @return
	 */
	public String addChannel() {

		// 入口日志
		log.info("---------------addChannel start---------------");

		log.info("渠道信息：" + channel);
		try {
			channelService.addChannel(channel);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------addChannel end---------------");

		return SUCCESS;
	}

	/**
	 * 将要修改的渠道
	 * 
	 * @return
	 */
	public String toUpdateChannel() {

		// 入口日志
		log.info("---------------toUpdateChannel start---------------");

		try {
			channel = channelService.queryChannelById(channelId);
			log.info("将要修改的渠道信息：" + channel);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------toUpdateChannel end---------------");

		return SUCCESS;
	}

	/**
	 * 修改渠道
	 * 
	 * @return
	 */
	public String updateChannel() {

		// 入口日志
		log.info("---------------updateChannel start---------------");

		try {
			channelService.updateChannel(channel);
			log.info("修改后的渠道信息：" + channel);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------updateChannel end---------------");

		return SUCCESS;
	}

	/**
	 * 删除渠道
	 * 
	 * @return
	 */
	public String deleteChannel() {

		// 入口日志
		log.info("---------------deleteChannel start---------------");

		try {
			channelService.deleteChannel(ids.split(","));
			log.info("删除的渠道：" + ids);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------deleteChannel end---------------");

		return SUCCESS;
	}

	public ChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(ChannelService channelService) {
		this.channelService = channelService;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Channel getSearch() {
		return search;
	}

	public void setSearch(Channel search) {
		this.search = search;
	}

	public List<Channel> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<Channel> channelList) {
		this.channelList = channelList;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
