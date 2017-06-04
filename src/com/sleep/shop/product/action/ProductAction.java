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
	//����cid
	private int cid;
	//���յ�ǰҳ��
	private int page;
	//����csid
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

	//��ʾ��Ʒ��ϸ��Ϣ�ķ���
	public String findByPid(){
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
	
	public String findByCid(){
		
		return "findByCid";
	}
//һ��������Ʒ��ʾ
	public String findByCidPage(){
		PageBean<Product> pagebean = productService.findByCidPage(cid,page);
		ActionContext.getContext().getValueStack().set("pagebean", pagebean);
		return "findByCidPage";
	}
	//����������Ʒ����ʾ
	public String findByCsidPage(){
		PageBean<Product> pagebean = productService.findByCsidPage(csid,page);
		ActionContext.getContext().getValueStack().set("pagebean", pagebean);
		return "findByCsidPage";
	}
}
