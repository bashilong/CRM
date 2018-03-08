package com.itheima.web.action;

import org.apache.struts2.ServletActionContext;

import com.itheima.bean.User;
import com.itheima.constant.Constant;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user==null){
			System.out.println("请先登录!");
			return "login";
		}
		return arg0.invoke();
	}

}
