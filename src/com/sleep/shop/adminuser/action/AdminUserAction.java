package com.sleep.shop.adminuser.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sleep.shop.adminuser.po.AdminUser;
import com.sleep.shop.adminuser.service.AdminUserService;



@SuppressWarnings("serial")
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{
	private AdminUserService adminUserService;
	private AdminUser adminUser;
	
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	//模型驱动
	public AdminUser getModel() {
		if(adminUser == null){
			adminUser = new AdminUser();
		}
		return adminUser;
	}
	
	//登录的方法
	public String login(){
		AdminUser existAdminUser = this.adminUserService.login(adminUser);

		if(existAdminUser == null){
			//登陆失败
			this.addActionError("用户名或者密码错误！请输入正确的用户名和密码！");
			return "loginFail";
		}else{
			//登录成功
			ServletActionContext.getRequest().getSession().setAttribute("adminUser", adminUser);
		return "loginSuccess";
		}
	}
	//显示所有的管理员
	public String findAll(){
		List<AdminUser> alist = this.adminUserService.findAll();
		ActionContext.getContext().getValueStack().set("alist",alist);
		return "findAllsuccess";
	}
}
