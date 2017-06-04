package com.sleep.shop.product.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sleep.shop.product.po.Product;
import com.sleep.shop.util.PageBean;

public class ProductDao {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Product> findHot() {
		//离线查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.eq("is_hot",1));
		criteria.addOrder(Order.desc("pdate"));
		List<Product> list = criteria.getExecutableCriteria(this.getSessionFactory().getCurrentSession())
				.setFirstResult(0).setMaxResults(10).list();
		//测试代码，打印出商品图片路径
		/*for(int i=0;i<list.size();i++){
			String s = list.get(i).getImage();
			System.out.println(s);
		}*/
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Product> findNew() {
		//条件查询
		List<Product> newplist = this.sessionFactory.getCurrentSession()
				.createCriteria(Product.class).addOrder(Order.desc("pdate"))
				.setFirstResult(0).setMaxResults(10).list();
		return newplist;
	}

	public Product findByPid(Integer pid) {
		return (Product) sessionFactory.getCurrentSession().get(Product.class, pid);
	}
	//查询所有的个数
	@SuppressWarnings({ "unchecked" })
	public int countAll(int cid) {
		String hql = "select count(*) from Product p join p.categorysecond cs join cs.category c where c.cid=:cid";
		List<Long> list = this.sessionFactory.getCurrentSession().createQuery(hql)
		.setInteger("cid", cid).list();
		if(list != null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<Product> findByCidPage(int cid, int page, int pagesize) {
		String hql = "select p from Product p join p.categorysecond cs join cs.category c where c.cid = :cid";
		List<Product> list = this.sessionFactory.getCurrentSession().createQuery(hql)
				.setInteger("cid", cid).setFirstResult((page-1)*pagesize)
				.setMaxResults(pagesize).list();
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	
	}

	@SuppressWarnings("unchecked")
	public int countCsidAll(int csid) {
		String hql = "select count(*) from Product p where p.categorysecond.csid = :csid";
		List<Long> list = this.sessionFactory.getCurrentSession().createQuery(hql)
				.setInteger("csid" , csid).list();
		if(list != null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<Product> findByCsidPage(int csid, int page, int pagesize) {
		String hql = "select p from Product p join p.categorysecond cs where cs.csid = :csid";
		List<Product> list = this.sessionFactory.getCurrentSession()
				.createQuery(hql).setInteger("csid", csid)
				.setFirstResult((page-1)*pagesize).setMaxResults(pagesize)
				.list();
		

		return list;
	}

	@SuppressWarnings("unchecked")
	public int findCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.sessionFactory.getCurrentSession().createQuery(hql).list();
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<Product> findByPage(int page, int pagesize) {
		String hql = "from Product order by pdate desc";
		List<Product> productlist = this.sessionFactory.getCurrentSession()
				.createQuery(hql).setFirstResult((page-1)*pagesize).setMaxResults(pagesize).list();
		if(productlist != null && productlist.size() > 0){
			return productlist;
		}
		return null;
	}

	public void save(Product product) {
		this.sessionFactory.getCurrentSession().save(product);
		
	}

	public void delete(Product product) {
		this.sessionFactory.getCurrentSession()
		.delete(product);
		
	}

	public void update(Product product) {
		this.sessionFactory.getCurrentSession().update(product);
		
	}

	
	
}
