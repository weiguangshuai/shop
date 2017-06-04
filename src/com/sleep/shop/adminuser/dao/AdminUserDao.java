package com.sleep.shop.adminuser.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.sleep.shop.adminuser.po.AdminUser;

public class AdminUserDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public AdminUser login(AdminUser adminUser) {
		String hql = "from AdminUser where username=:username and password=:password";
		List<AdminUser> list = this.sessionFactory.getCurrentSession()
				.createQuery(hql).setString("username", adminUser.getUsername())
				.setString("password", adminUser.getPassword()).list();
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<AdminUser> findAll() {
		String hql = "from AdminUser";
		List<AdminUser> alist = this.sessionFactory.getCurrentSession()
				.createQuery(hql).list();
		if(alist != null && alist.size() > 0){
			return alist;
		}
		return null;
	}
	

}
