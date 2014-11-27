package com.a8.qdm.sdk.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SDK工具类
 * 
 * @author Lund
 * 
 */
public class SdkUtil {

	/**
	 * 生成订单号
	 * 
	 * @param orderStart
	 *            订单号起始标记
	 * @return 订单号
	 */
	public static String getOrderNo(String orderStart) {
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmssSSS")
				.format(new Date());
		int randomNo = (int) (Math.random() * 100);
		String orderNo = "Q000" + orderStart + currentTime + randomNo;

		return orderNo;
	}

	/**
	 * 当前系统时间
	 * 
	 * @return
	 */
	public static String currentTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	/**
	 * 时间格式化
	 * 
	 * @param dateStr
	 *            日期
	 * @return
	 * @throws Exception
	 */
	public static String dateFormat(String dateStr) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = sdf.parse(dateStr);
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sdf.format(date);
	}

	/**
	 * 按概率计算是否发送通知
	 * 
	 * @param chance
	 *            概率
	 * @return true：发送|false：不发送
	 */
	public static boolean isSend(String chance) throws Exception {
		boolean isSend = true;
		int randomNo = (int) (Math.random() * 100 + 1);
		if (randomNo > Integer
				.parseInt(chance.substring(0, chance.length() - 1))) {
			isSend = false;
		}

		return isSend;
	}

	/**
	 * 将null转为空字符串
	 * 
	 * @param str
	 *            字符串
	 * @return 返回值
	 */
	public static String nullToString(String str) {
		if (str == null) {
			return "";
		} else {
			return str;
		}
	}
}
