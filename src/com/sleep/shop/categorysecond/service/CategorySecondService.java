package com.sleep.shop.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sleep.shop.categorysecond.dao.CategorySecondDao;
import com.sleep.shop.categorysecond.po.CategorySecond;
import com.sleep.shop.util.PageBean;

@Transactional
public class CategorySecondService {
	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
	//二级分类查找
	public PageBean<CategorySecond> findByPage(int page) {
		PageBean<CategorySecond> pagebean = new PageBean<>();
		pagebean.setPage(page);
		int pagesize = 10;
		pagebean.setPagesize(pagesize);
		int totalsize;
		totalsize = categorySecondDao.allCount();
		if(totalsize % pagesize == 0){
			pagebean.setTotal(totalsize/pagesize);
		}else{
			pagebean.setTotal(totalsize/pagesize + 1);
		}
		pagebean.setList(categorySecondDao.findByPage(page,pagesize));
		return pagebean;
	}
	public void save(CategorySecond categorySecond) {
		this.categorySecondDao.save(categorySecond);
		
	}
	public void delete(CategorySecond categorySecond) {
		this.categorySecondDao.delete(categorySecond);
		
	}
	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}
	public void update(CategorySecond categorySecond) {
		this.categorySecondDao.update(categorySecond);
		
	}
	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}
	
	
	
	
}
