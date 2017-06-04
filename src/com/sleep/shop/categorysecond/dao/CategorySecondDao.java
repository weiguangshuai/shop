package com.sleep.shop.categorysecond.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.sleep.shop.categorysecond.po.CategorySecond;

public class CategorySecondDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public int allCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = this.sessionFactory.getCurrentSession()
				.createQuery(hql).list();
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<CategorySecond> findByPage(int page, int pagesize) {
		String hql = "from CategorySecond order by csid asc";
		List<CategorySecond> list = this.sessionFactory.getCurrentSession()
				.createQuery(hql).setFirstResult((page-1)*pagesize).setMaxResults(pagesize)
				.list();
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	public void save(CategorySecond categorySecond) {
		this.sessionFactory.getCurrentSession().save(categorySecond);
		
	}

	public void delete(CategorySecond categorySecond) {
		this.sessionFactory.getCurrentSession().delete(categorySecond);
		
	}

	public CategorySecond findByCsid(Integer csid) {
		return (CategorySecond) this.sessionFactory.getCurrentSession().get(CategorySecond.class , csid);
	}

	public void update(CategorySecond categorySecond) {
		this.sessionFactory.getCurrentSession().update(categorySecond);
		
	}

	@SuppressWarnings("unchecked")
	public List<CategorySecond> findAll() {
		String hql = "from CategorySecond";
		List<CategorySecond> cslist = this.sessionFactory.getCurrentSession()
				.createQuery(hql).list();
		if(cslist != null && cslist.size() > 0){
			return cslist;
		}
		return null;
	}

	
	
}
