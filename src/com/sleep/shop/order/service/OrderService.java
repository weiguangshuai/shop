package com.sleep.shop.order.service;

import org.springframework.transaction.annotation.Transactional;

import com.sleep.shop.order.dao.OrderDao;
import com.sleep.shop.order.po.Orders;
import com.sleep.shop.util.PageBean;

@Transactional
public class OrderService {
	//×¢Èëdao
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
//±£´æ¶©µ¥
	public void save(Orders orders) {
		orderDao.save(orders);
		
	}
	public PageBean<Orders> findByUid(Integer uid, int page) {
		PageBean<Orders> pagebean = new PageBean<>();
		int total = this.orderDao.countAll(uid);
		int totalpage;
		int pagesize = 4;
		if(total % pagesize == 0){
			totalpage = total/pagesize;
		}else{
			totalpage = total/pagesize + 1;
		}
		pagebean.setTotal(totalpage);
		pagebean.setPagesize(pagesize);
		pagebean.setPage(page);
		pagebean.setList(orderDao.findByCidPage(uid,page,pagesize));
		return pagebean;
	}
	public Orders findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}
	public void update(Orders currentorder) {
		this.orderDao.update(currentorder);
		
	}
	
}
