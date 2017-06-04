package com.sleep.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sleep.shop.cart.po.Cart;
import com.sleep.shop.cart.po.CartItem;
import com.sleep.shop.product.po.Product;
import com.sleep.shop.product.service.ProductService;

public class CartAction extends ActionSupport{
	//����pid
	private int pid;
	//����count
	private int count;
	
	private ProductService productService;
	//��session�л�ȡ���ﳵ
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
	
	//��ӵ����ﳵ
	public String addCart(){
		//��װһ��CartItem����
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		//����pid��ȡproduct
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		Cart cart = this.getCart();
		cart.addCart(cartItem);
		return "addCart";
	}
	
	//��չ��ﳵ
	public String clearCart(){
		Cart cart = this.getCart();
		cart.clearCart();
		return "clearCart";
	}
	//�ӹ��ﳵ���Ƴ�������
	public String removeCart(){
		Cart cart = this.getCart();
		cart.removeCart(pid);

		return "removeCart";
	}
	//�ҵĹ��ﳵ
	public String showCart(){
		
		return "showcartsuccess";
	}
}
