package com.sleep.shop.user.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.sleep.shop.user.po.User;

public class UserDao {
//�û��־ò����
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//�����ֲ�ѯ�Ƿ񷵻ظ��û�
	@SuppressWarnings("unchecked")
	public User findByUsername(String username){
		String hql = "from User where username=:userName";
		List<User> list = getSessionFactory().getCurrentSession()
				.createQuery(hql).setString("userName", username).list();
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;

	}
//ע���û�	
	public void save(User user) {
		getSessionFactory().getCurrentSession().save(user);
	}

	public User login(User user) {
		String hql = "from User where username=:username and password=:password";
		List<User> list = this.getSessionFactory().getCurrentSession()
				.createQuery(hql).setString("username", user.getUsername()).setString("password", user.getPassword())
				.list();
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}
