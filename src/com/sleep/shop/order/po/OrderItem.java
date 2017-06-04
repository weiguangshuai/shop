package com.sleep.shop.order.po;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sleep.shop.product.po.Product;
@Entity
@Table(name="orderitem")
public class OrderItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer itemid;
	private Integer count;
	private double subtotal;
	@ManyToOne(targetEntity=Product.class,fetch=FetchType.EAGER)
	@JoinColumn(name="pid",referencedColumnName="pid")
	private Product product;
	@ManyToOne(targetEntity=Orders.class,fetch=FetchType.EAGER)
	@JoinColumn(name="oid",referencedColumnName="oid")
	private Orders orders;
	public Integer getItemid() {
		return itemid;
	}
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	
	
}
