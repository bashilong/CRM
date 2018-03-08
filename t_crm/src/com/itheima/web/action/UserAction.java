package com.itheima.web.action;

import org.apache.struts2.ServletActionContext;

import com.itheima.bean.User;
import com.itheima.constant.Constant;
import com.itheima.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	
	private User user = new User();
	
	@Override
	public User getModel() {
		return user;
	}
	
	private UserService userService;
	public void setUserService(UserService userService) {
		
		this.userService = userService;
	}
	/**
	 * 用户注册
	 * @return
	 */
	public String register(){
		user.setUser_state('1');
		userService.register(user);
		return Constant.LOGIN_ERROR;
	}
	
	/**
	 * 用户登录
	 */
	public String login(){
		User loginUser = userService.login(user);
		if(loginUser!=null){
			ServletActionContext.getRequest().getSession().setAttribute("user", loginUser);
			return Constant.LOGIN_SUCCESS;
		}
		//ActionContext.getContext().getValueStack().set( "msg" , "账号或者密码错误!");
			addFieldError("msg", "用户名或密码错误");
			return Constant.LOGIN_ERROR;
		
	}
	
}
