package com.sleep.shop.cart.po;

import com.sleep.shop.product.po.Product;

public class CartItem {
	private int count;
	private Product product;
	private double subtotal;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public double getSubtotal() {
		return count*product.getShop_price();
	}
	
}
