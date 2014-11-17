package com.a8.qdm.sdk.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.a8.qdm.config.service.GamePayService;
import com.a8.qdm.query.dao.bean.Device;
import com.a8.qdm.query.dao.bean.GameDevice;
import com.a8.qdm.query.service.DeviceService;
import com.a8.qdm.query.service.bean.DeviceServiceBean;
import com.opensymphony.xwork2.ActionSupport;

/**
 * SDK入口
 * 
 * @author Lund
 * 
 */
public class SdkAction extends ActionSupport {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 2032708112684922886L;

	/**
	 * 发送给SDK结果1：成功
	 */
	private static final String SDK_SUCCESS = "1";

	/**
	 * 发送给SDK结果0：失败
	 */
	private static final String SDK_FAIL = "0";

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(SdkAction.class);

	/**
	 * deviceService接口注入
	 */
	private DeviceService deviceService;

	/**
	 * gamePayService接口注入
	 */
	private GamePayService gamePayService;

	/**
	 * 设备同步
	 * 
	 * @return 无需跳转页面
	 */
	public String deviceSyn() {

		// 入口日志
		log.info("---------------deviceSyn start---------------");

		// 获取设备信息并封装
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject jsonObject = new JSONObject();
		Device device = new Device();
		GameDevice gameDevice = new GameDevice();
		DeviceServiceBean deviceServiceBean = new DeviceServiceBean();
		device.setDeviceId(request.getParameter("deviceId"));
		device.setDeviceType(request.getParameter("deviceType"));
		device.setImei(request.getParameter("imei"));
		device.setImsi(request.getParameter("imsi"));
		device.setSdkVersion(request.getParameter("sdkVersion"));
		device.setSystemVersion(request.getParameter("systemVersion"));
		log.info("设备信息：" + device);
		gameDevice.setGameId(request.getParameter("gameId"));
		gameDevice.setDeviceId(request.getParameter("deviceId"));
		deviceServiceBean.setDevice(device);
		deviceServiceBean.setGameDevice(gameDevice);

		// 添加设备信息
		try {
			deviceService.addDevice(deviceServiceBean);

			// 返回给SDK请求状态1：成功
			jsonObject.put("state", SDK_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);

			// 返回给SDK请求状态0：失败
			jsonObject.put("state", SDK_FAIL);
		} finally {
			try {
				// 发送给SDK
				response.getWriter().print(jsonObject);
				log.info("发送给轻点支付SDK内容：" + jsonObject);
			} catch (IOException io) {
				io.printStackTrace();
				log.error(io);
			}
		}

		// 出口日志
		log.info("---------------deviceSyn end---------------");
		return null;
	}

	/**
	 * 获取支付列表
	 * 
	 * @return 无需跳转页面
	 */
	public String queryPay() {

		// 入口日志
		log.info("---------------queryPay start---------------");

		List<String> payList = new ArrayList<String>();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject jsonObject = new JSONObject();
		String gameId = request.getParameter("gameId");
		log.info("产品ID：" + gameId);

		// 通过游戏ID获取支付列表并发送给SDK
		try {
			payList = gamePayService.queryPayId(gameId);

			if (!payList.isEmpty()) {
				JSONArray jsonArray = JSONArray.fromObject(payList);
				jsonObject.put("payList", jsonArray);

				// 返回给SDK请求状态1：成功
				jsonObject.put("state", SDK_SUCCESS);
			} else {

				// 返回给SDK请求状态0：失败
				jsonObject.put("state", SDK_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);

			// 返回给SDK请求状态0：失败
			jsonObject.put("state", SDK_FAIL);
		} finally {
			try {
				// 发送给SDK
				response.getWriter().print(jsonObject);
				log.info("发送给轻点支付SDK内容：" + jsonObject);
			} catch (IOException io) {
				io.printStackTrace();
				log.error(io);
			}
		}

		// 出口日志
		log.info("---------------queryPay end---------------");
		return null;
	}

	public DeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public GamePayService getGamePayService() {
		return gamePayService;
	}

	public void setGamePayService(GamePayService gamePayService) {
		this.gamePayService = gamePayService;
	}
}
