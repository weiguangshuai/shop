package com.sleep.shop.product.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sleep.shop.product.po.Product;
import com.sleep.shop.product.service.ProductService;
import com.sleep.shop.util.PageBean;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product product;
	private ProductService productService;
	//接受cid
	private int cid;
	//接收当前页数
	private int page;
	//接收csid
	private int csid;
	
	
	public int getCsid() {
		return csid;
	}

	public void setCsid(int csid) {
		this.csid = csid;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Product getModel() {
		if(product == null){
			product = new Product();
		}
		return product;
	}
	
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	//显示商品详细信息的方法
	public String findByPid(){
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
	
	public String findByCid(){
		
		return "findByCid";
	}
//一级分类商品显示
	public String findByCidPage(){
		PageBean<Product> pagebean = productService.findByCidPage(cid,page);
		ActionContext.getContext().getValueStack().set("pagebean", pagebean);
		return "findByCidPage";
	}
	//二级分类商品的显示
	public String findByCsidPage(){
		PageBean<Product> pagebean = productService.findByCsidPage(csid,page);
		ActionContext.getContext().getValueStack().set("pagebean", pagebean);
		return "findByCsidPage";
	}
}
