package com.sleep.shop.user.action;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

/*
 * �û�ģ���Action
 */
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sleep.shop.user.po.User;
import com.sleep.shop.user.service.UserService;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user;
	//��֤��
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
	//ҵ���߼������ע��
	public UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/*
	 * ��תע��ҳ��ķ���
	 */
	public String registPage(){
		return "registPage";
	}
	//ajax�����첽�ķ���
	public String findByName() throws IOException{
		User existUser = userService.findByUsername(user.getUsername());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("text/html;charset=UTF-8");

		if(existUser != null){
			//��ѯ���û������û�������
			response.getWriter().println("<font color='red'>�û����Ѿ�����</font>");
			
		}else{
			//û�в�ѯ���û������û���������
			response.getWriter().println("<font color='red'>�û���������</font>");
			
		}
		return NONE;
		
	}
	//�û�ע��ķ���
	public String regist(){
		String code1 = (String)ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(code1)){
			this.addActionError("��֤���������");
			return "checkFail";
		}
		userService.save(user);
		return SUCCESS;
	}
	//ת����¼����
	public String loginPage(){
		return "loginPage";
	}
	//��¼�ķ���
	public String login(){
		User existuser = userService.login(user);
		if(existuser == null){
			//��¼ʧ��
			this.addActionError("��¼ʧ�ܣ��û��������������");
			return LOGIN;
		}else{
			//���û�����Ϣ����session�У������ҳ�����ת
			ServletActionContext.getRequest().getSession().setAttribute("existuser", existuser);
			return "loginSuccess";
		}
	}
	//�˳��ķ���
	public String quit(){
		//����session
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
	
} 

