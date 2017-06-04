package com.sleep.shop.category.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.sleep.shop.category.po.Category;



public class CategoryDao {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Category> findAll() {
		String hql = "from Category";
		List<Category> list = this.sessionFactory.getCurrentSession()
				.createQuery(hql).list();
		
		return list;
	}

	public void save(Category category) {
		this.sessionFactory.getCurrentSession().save(category);
	}

	
	public Category findByCid(Integer cid) {
		Category category = (Category) this.sessionFactory.getCurrentSession().get(Category.class, cid);
		return category;
	}

	public void delete(Category category) {
		this.sessionFactory.getCurrentSession()
		.delete(category);
		
	}

	public void update(Category category) {
		this.sessionFactory.getCurrentSession()
		.update(category);
	}

	
}
