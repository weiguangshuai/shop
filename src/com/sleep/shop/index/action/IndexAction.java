package com.sleep.shop.index.action;
import java.util.Iterator;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
/*
 * 主页访问的Action
 */
import com.opensymphony.xwork2.ActionSupport;
import com.sleep.shop.category.po.Category;
import com.sleep.shop.category.service.CategoryService;
import com.sleep.shop.product.po.Product;
import com.sleep.shop.product.service.ProductService;

public class IndexAction extends ActionSupport {
	
	private CategoryService categoryService;	
	private ProductService productService;
	
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public String execute(){
		//一级分类查询结果
		List<Category> categoryList = categoryService.findAll();
		ActionContext.getContext().getSession().put("categoryList", categoryList);
		//查询热门商品
		List<Product> productList = productService.findHot();
		//将热门商品存入到值栈中
		ActionContext.getContext().getValueStack().set("productList", productList);
		//查询最新的商品
		List<Product> newpList = productService.findNew();
		//将最新商品存入值栈中
		ActionContext.getContext().getValueStack().set("newpList", newpList);
		return "index";
	}
}
