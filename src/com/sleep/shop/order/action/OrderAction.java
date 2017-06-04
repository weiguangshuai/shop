package com.sleep.shop.order.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sleep.shop.cart.po.Cart;
import com.sleep.shop.cart.po.CartItem;
import com.sleep.shop.order.po.OrderItem;
import com.sleep.shop.order.po.Orders;
import com.sleep.shop.order.service.OrderService;
import com.sleep.shop.user.po.User;
import com.sleep.shop.util.PageBean;

/*
 * 订单管理的Action
 */
@SuppressWarnings("serial")
public class OrderAction extends ActionSupport implements ModelDriven<Orders>{
	private Orders orders;
	public Orders getModel() {
		if(orders == null){
			orders = new Orders();
		}
		return orders;
	}
	private int page;
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	//注入service
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	//订单跳转
	public String save(){
		//订单数据补全
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			this.addActionError("您还没有购物，请先去购物");
			return "msg";
		}
		orders.setOrdertime(new Date());
		orders.setState(1);//1、未付款 2、已经付款，但是没有发货 3、已经发货，但是没有确认收货 4、交易完成
		orders.setTotal(cart.getTotal());
		//设置订单中的订单项
		for (CartItem cartItem : cart.getCartitem()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setOrders(orders);
			orderItem.setProduct(cartItem.getProduct());
			
			orders.getOrderItems().add(orderItem);
		}
		//设置订单所属的用户
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("existuser");
		if(user == null){
			this.addActionError("您还没有登陆，请先登录");
			return "login";
		}
		orders.setUser(user);
		orderService.save(orders);
		return "savesuccess";
	}
	//显示个人订单
	public String myorder(){
		User user = (User) ServletActionContext.getRequest()
				.getSession().getAttribute("existuser");
		PageBean<Orders> pagebean = this.orderService.findByUid(user.getUid(),page);
		ActionContext.getContext().getValueStack().set("pagebean", pagebean);
		return "myordersuccess";
	}
	//根据订单编号查询订单
	public String findByOid(){
		orders = this.orderService.findByOid(orders.getOid());
		return "findsuccess";
	}
	//订单付款方法
	public String pay(){
		Orders currentorder = this.orderService.findByOid(orders.getOid());
		currentorder.setAddress(orders.getAddress());
		currentorder.setName(orders.getName());
		currentorder.setPhone(orders.getPhone());
		currentorder.setState(2);
		this.orderService.update(currentorder);
		return "paysuccess";
	}
}
