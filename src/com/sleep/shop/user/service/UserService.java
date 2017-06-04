package com.sleep.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import com.sleep.shop.user.dao.UserDao;
import com.sleep.shop.user.po.User;
import com.sleep.shop.util.UUIDUtils;

@Transactional
public class UserService {
	//注入dao
	private UserDao userdao;

	public UserDao getUserdao() {
		return userdao;
	}

	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}
	//按用户名查询用户的方法
	public User findByUsername(String username){
		return userdao.findByUsername(username);
	}

	public void save(User user) {
		user.setState(0);//0：代表未激活 1：代表已经激活
		String code = UUIDUtils.getUUIT()+UUIDUtils.getUUIT();
		user.setCode(code);
		userdao.save(user);
	}
	//登录的方法
	public User login(User user) {
		return userdao.login(user);
	}
	
}
