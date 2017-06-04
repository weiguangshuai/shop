package com.sleep.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sleep.shop.cart.po.Cart;
import com.sleep.shop.cart.po.CartItem;
import com.sleep.shop.product.po.Product;
import com.sleep.shop.product.service.ProductService;

public class CartAction extends ActionSupport{
	//接收pid
	private int pid;
	//接收count
	private int count;
	
	private ProductService productService;
	//从session中获取购物车
	private Cart getCart(){
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	//添加到购物车
	public String addCart(){
		//封装一个CartItem对象
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		//根据pid获取product
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		Cart cart = this.getCart();
		cart.addCart(cartItem);
		return "addCart";
	}
	
	//清空购物车
	public String clearCart(){
		Cart cart = this.getCart();
		cart.clearCart();
		return "clearCart";
	}
	//从购物车中移除购物项
	public String removeCart(){
		Cart cart = this.getCart();
		cart.removeCart(pid);

		return "removeCart";
	}
	//我的购物车
	public String showCart(){
		
		return "showcartsuccess";
	}
}
