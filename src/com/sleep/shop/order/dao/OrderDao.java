package com.sleep.shop.order.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.sleep.shop.order.po.Orders;

public class OrderDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(Orders orders) {
		this.sessionFactory.getCurrentSession()
		.save(orders);
		
	}

	@SuppressWarnings("unchecked")
	public int countAll(Integer uid) {
		String hql = "select count(*) from Orders o where o.user.uid = :uid";
		List<Long> count = this.sessionFactory.getCurrentSession()
				.createQuery(hql).setInteger("uid",uid).list();
		if(count != null&&count.size() > 0){
			return count.get(0).intValue();
		}
		return 0;
	}

	public List<Orders> findByCidPage(Integer uid, int page, int pagesize) {
		String hql = "select o from Orders o join o.user u where u.uid =:uid";
		@SuppressWarnings("unchecked")
		List<Orders> list = this.sessionFactory.getCurrentSession()
				.createQuery(hql).setInteger("uid",uid)
				.setFirstResult((page-1) * pagesize).setMaxResults(pagesize).list();
		if(list != null&&list.size() > 0){
			return list;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Orders findByOid(Integer oid) {
		String sql = "from Orders where oid=:oid";
		List<Orders> list = this.sessionFactory.getCurrentSession()
				.createQuery(sql).setInteger("oid", oid).list();
		if(list != null&&list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public void update(Orders currentorder) {
		this.sessionFactory.getCurrentSession()
		.update(currentorder);
		
	}
	
}
