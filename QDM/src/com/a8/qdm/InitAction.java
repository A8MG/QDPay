package com.a8.qdm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 入口Action类
 * 
 * @author Lund
 * 
 */
public abstract class InitAction extends ActionSupport {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -8970060590593483253L;

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(InitAction.class);

	/**
	 * 分页
	 */
	public Page page = new Page();

	/**
	 * 开始页
	 */
	private int begin;

	/**
	 * 结束页
	 */
	private int end;

	/**
	 * 菜单查询
	 * 
	 * @return 列表页面
	 */
	public String query() {

		// 入口日志
		log.info("---------------query start---------------");

		// 查询列表
		try {
			queryImpl();
			if (page.getCurrentPage() % 5 == 0) {
				begin = (page.getCurrentPage() / 5 - 1) * 5 + 1;
				end = page.getCurrentPage();
			} else {
				begin = page.getCurrentPage() / 5 * 5 + 1;
				end = (page.getCurrentPage() / 5 + 1) * 5;
				if (end > page.getPageCount()) {
					end = page.getPageCount();
				}
			}
			log.info("开始页：" + begin + "|" + "结束页：" + end);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		// 出口日志
		log.info("---------------query end---------------");

		return SUCCESS;
	}

	/**
	 * 导出
	 * 
	 * @return 返回结果
	 */
	public String export() {

		// 入口日志
		log.info("---------------export start---------------");

		// 导出
		try {
			exportImpl();
		} catch (Exception e) {
			log.error(e);
		}

		// 出口日志
		log.info("---------------export end---------------");

		return SUCCESS;
	}

	/**
	 * 查询实现
	 * 
	 * @param bean
	 *            查询条件
	 */
	public abstract void queryImpl() throws Exception;

	/**
	 * 导出实现
	 */
	public void exportImpl() throws Exception {

	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
}
