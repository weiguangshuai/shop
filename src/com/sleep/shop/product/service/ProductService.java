package com.sleep.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sleep.shop.product.dao.ProductDao;
import com.sleep.shop.product.po.Product;
import com.sleep.shop.util.PageBean;

@Transactional
public class ProductService {
	private ProductDao productDao;

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public List<Product> findHot() {
		return productDao.findHot();
	}

	public List<Product> findNew() {
		return productDao.findNew();
	}

	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}
	
	public PageBean<Product> findByCidPage(int cid, int page) {
		PageBean<Product> pagebean = new PageBean<Product>();
		int total = this.countAll(cid);
		int totalpage;
		int pagesize = 8;
		if(total % pagesize == 0){
			totalpage = total/pagesize;
		}else{
			totalpage = total/pagesize + 1;
		}
		pagebean.setTotal(totalpage);
		pagebean.setPagesize(pagesize);
		pagebean.setPage(page);
		pagebean.setList(productDao.findByCidPage(cid,page,pagesize));
		return pagebean;
	}
	public int countAll(int cid){
		return productDao.countAll(cid);
	}

	public PageBean<Product> findByCsidPage(int csid, int page) {
		PageBean<Product> pagebean = new PageBean<>();
		pagebean.setPage(page);
		int total = this.countCsidAll(csid);
		int pagesize = 8;
		int totalpage;
		if(total % pagesize == 0){
			totalpage = total/pagesize;
		}else{
			totalpage = total/pagesize + 1;
		}
		pagebean.setTotal(totalpage);
		pagebean.setPagesize(pagesize);
		pagebean.setList(productDao.findByCsidPage(csid,page,pagesize));
		return pagebean;
	}

	private int countCsidAll(int csid) {
		return productDao.countCsidAll(csid);
	}

	public PageBean<Product> findByPage(int page) {
		PageBean<Product> pagebean = new PageBean<Product>();
		pagebean.setPage(page);
		int pagesize = 10;
		int totalpage;
		int total = productDao.findCount();
		if(total % pagesize == 0){
			totalpage = total/pagesize;
		}else{
			totalpage = total/pagesize + 1;
		}
		pagebean.setTotal(totalpage);
		pagebean.setList(productDao.findByPage(page , pagesize));
		return pagebean;

	}

	public void save(Product product) {
		productDao.save(product);
		
	}

	public void delete(Product product) {
		productDao.delete(product);
		
	}

	public void update(Product product) {
		productDao.update(product);
		
	}
	
	
}