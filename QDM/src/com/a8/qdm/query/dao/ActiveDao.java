package com.a8.qdm.query.dao;

import com.a8.qdm.query.dao.bean.Active;

/**
 * 用户行为Dao
 * 
 * @author lund
 *
 */
public interface ActiveDao {
	
 /**
  * 添加用户登录行为
  * @param active 用户行为
  * @throws Exception
  */
 void addLoginActive(Active active)throws Exception;
 
 /**
  * 修改用户支付意愿行为
  * @param active 用户行为
  * @return
  * @throws Exception
  */
 void updatePrepay(Active active)throws Exception;
 
 /**
  * 查询用户的用户行为
  * @param active
  * @return 返回该用户的行为记录
  * @throws Exception
  */
 Active selectActive(Active active)throws Exception;
}
