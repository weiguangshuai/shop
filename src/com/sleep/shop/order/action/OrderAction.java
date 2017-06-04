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
 * ���������Action
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
	//ע��service
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	//������ת
	public String save(){
		//�������ݲ�ȫ
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			this.addActionError("����û�й������ȥ����");
			return "msg";
		}
		orders.setOrdertime(new Date());
		orders.setState(1);//1��δ���� 2���Ѿ��������û�з��� 3���Ѿ�����������û��ȷ���ջ� 4���������
		orders.setTotal(cart.getTotal());
		//���ö����еĶ�����
		for (CartItem cartItem : cart.getCartitem()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setOrders(orders);
			orderItem.setProduct(cartItem.getProduct());
			
			orders.getOrderItems().add(orderItem);
		}
		//���ö����������û�
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("existuser");
		if(user == null){
			this.addActionError("����û�е�½�����ȵ�¼");
			return "login";
		}
		orders.setUser(user);
		orderService.save(orders);
		return "savesuccess";
	}
	//��ʾ���˶���
	public String myorder(){
		User user = (User) ServletActionContext.getRequest()
				.getSession().getAttribute("existuser");
		PageBean<Orders> pagebean = this.orderService.findByUid(user.getUid(),page);
		ActionContext.getContext().getValueStack().set("pagebean", pagebean);
		return "myordersuccess";
	}
	//���ݶ�����Ų�ѯ����
	public String findByOid(){
		orders = this.orderService.findByOid(orders.getOid());
		return "findsuccess";
	}
	//���������
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
