package com.sleep.shop.adminuser.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sleep.shop.adminuser.dao.AdminUserDao;
import com.sleep.shop.adminuser.po.AdminUser;

@Transactional
public class AdminUserService {
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	public AdminUser login(AdminUser adminUser) {
		return this.adminUserDao.login(adminUser);
	}

	public List<AdminUser> findAll() {
		
		return adminUserDao.findAll();
	}
	
}
