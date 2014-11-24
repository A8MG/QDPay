package com.a8.qdm.config.util;

import java.util.Random;

/**
 * 配置工具
 * 
 * @author lund
 *
 */
public class ConfigUtil {

	/**
	 * 生成密钥
	 * 
	 * @param length
	 *            密钥长度
	 * 
	 * @return 密钥
	 */
	public static String makeKeyt(int length) {
		String keyt = "";
		Random random = new Random();
		char[] range = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ"
				.toCharArray();
		for (int i = 0; i < length; i++) {
			keyt += range[random.nextInt(62)];
		}

		return keyt.trim();
	}
}
