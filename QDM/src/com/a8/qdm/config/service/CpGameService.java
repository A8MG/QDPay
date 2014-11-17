package com.a8.qdm.config.service;

/**
 * 合作方与产品业务接口
 * 
 * @author lund
 *
 */
public interface CpGameService {

	/**
	 * 合作方是否绑定产品
	 * 
	 * @param cpId
	 *            合作方ID
	 * @return true:绑定|false:未绑定
	 * @throws Exception
	 */
	boolean bindGame(String[] cpId) throws Exception;
}
