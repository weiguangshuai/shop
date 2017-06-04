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
	//ģ������
	public AdminUser getModel() {
		if(adminUser == null){
			adminUser = new AdminUser();
		}
		return adminUser;
	}
	
	//��¼�ķ���
	public String login(){
		AdminUser existAdminUser = this.adminUserService.login(adminUser);

		if(existAdminUser == null){
			//��½ʧ��
			this.addActionError("�û����������������������ȷ���û��������룡");
			return "loginFail";
		}else{
			//��¼�ɹ�
			ServletActionContext.getRequest().getSession().setAttribute("adminUser", adminUser);
		return "loginSuccess";
		}
	}
	//��ʾ���еĹ���Ա
	public String findAll(){
		List<AdminUser> alist = this.adminUserService.findAll();
		ActionContext.getContext().getValueStack().set("alist",alist);
		return "findAllsuccess";
	}
}
