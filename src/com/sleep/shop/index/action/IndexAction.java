package com.sleep.shop.index.action;
import java.util.Iterator;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
/*
 * ��ҳ���ʵ�Action
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
		//һ�������ѯ���
		List<Category> categoryList = categoryService.findAll();
		ActionContext.getContext().getSession().put("categoryList", categoryList);
		//��ѯ������Ʒ
		List<Product> productList = productService.findHot();
		//��������Ʒ���뵽ֵջ��
		ActionContext.getContext().getValueStack().set("productList", productList);
		//��ѯ���µ���Ʒ
		List<Product> newpList = productService.findNew();
		//��������Ʒ����ֵջ��
		ActionContext.getContext().getValueStack().set("newpList", newpList);
		return "index";
	}
}
