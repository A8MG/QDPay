package com.a8.qdm.query.dao;

import com.a8.qdm.query.dao.bean.Active;

/**
 * 用户行为Dao
 * 
 * @author lund
 *
 */
public interface ActiveDao {
 // 添加用户登录行为
 void addLoginActive(Active active)throws Exception;
 // 添加用户付费意愿
 int addPayActive(Active active)throws Exception;
 // 查询用户行为
 int selectActive(Active active)throws Exception;
}
