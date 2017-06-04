package com.sleep.shop.user.action;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

/*
 * 用户模块的Action
 */
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sleep.shop.user.po.User;
import com.sleep.shop.user.service.UserService;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user;
	//验证码
	private String checkcode;
	
	public String getCheckcode() {
		return checkcode;
	}
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	public User getModel() {
		if(user == null){
			user = new User();
		}
		return user;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	//业务逻辑组件的注入
	public UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/*
	 * 跳转注册页面的方法
	 */
	public String registPage(){
		return "registPage";
	}
	//ajax进行异步的方法
	public String findByName() throws IOException{
		User existUser = userService.findByUsername(user.getUsername());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("text/html;charset=UTF-8");

		if(existUser != null){
			//查询到用户名，用户名存在
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
			
		}else{
			//没有查询到用户名，用户名不存在
			response.getWriter().println("<font color='red'>用户名可以用</font>");
			
		}
		return NONE;
		
	}
	//用户注册的方法
	public String regist(){
		String code1 = (String)ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(code1)){
			this.addActionError("验证码输入错误");
			return "checkFail";
		}
		userService.save(user);
		return SUCCESS;
	}
	//转到登录界面
	public String loginPage(){
		return "loginPage";
	}
	//登录的方法
	public String login(){
		User existuser = userService.login(user);
		if(existuser == null){
			//登录失败
			this.addActionError("登录失败，用户名或者密码错误");
			return LOGIN;
		}else{
			//将用户的信息存入session中，再完成页面的跳转
			ServletActionContext.getRequest().getSession().setAttribute("existuser", existuser);
			return "loginSuccess";
		}
	}
	//退出的方法
	public String quit(){
		//销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
	
} 

