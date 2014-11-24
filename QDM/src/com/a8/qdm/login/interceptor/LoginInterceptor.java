package com.a8.qdm.login.interceptor;

import java.util.Map;

import com.a8.qdm.login.action.LoginAction;
import com.a8.qdm.sdk.action.AliAction;
import com.a8.qdm.sdk.action.SdkAction;
import com.a8.qdm.sdk.action.SmsAction;
import com.a8.qdm.sdk.action.UpmpAction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 登录拦截器
 * 
 * @author lund
 *
 */
public class LoginInterceptor extends AbstractInterceptor {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 5627433481245166219L;

	/**
	 * 实现
	 */
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object user = session.get("user");
		Action action = (Action) actionInvocation.getAction();
		if (action instanceof LoginAction || action instanceof AliAction
				|| action instanceof SdkAction || action instanceof SmsAction
				|| action instanceof UpmpAction || user != null) {
			return actionInvocation.invoke();
		} else {
			return Action.LOGIN;
		}
	}

}
