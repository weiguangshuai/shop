package com.sleep.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import com.sleep.shop.user.dao.UserDao;
import com.sleep.shop.user.po.User;
import com.sleep.shop.util.UUIDUtils;

@Transactional
public class UserService {
	//ע��dao
	private UserDao userdao;

	public UserDao getUserdao() {
		return userdao;
	}

	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}
	//���û�����ѯ�û��ķ���
	public User findByUsername(String username){
		return userdao.findByUsername(username);
	}

	public void save(User user) {
		user.setState(0);//0������δ���� 1�������Ѿ�����
		String code = UUIDUtils.getUUIT()+UUIDUtils.getUUIT();
		user.setCode(code);
		userdao.save(user);
	}
	//��¼�ķ���
	public User login(User user) {
		return userdao.login(user);
	}
	
}
