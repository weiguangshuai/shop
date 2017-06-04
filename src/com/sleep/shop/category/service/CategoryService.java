package com.sleep.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sleep.shop.category.dao.CategoryDao;
import com.sleep.shop.category.po.Category;

@Transactional
public class CategoryService {
	//×¢ÈëDAO×é¼þ
	private CategoryDao categoryDao;

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	public void save(Category category) {
		this.categoryDao.save(category);
		
	}

	

	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}

	public void delete(Category category) {
		this.categoryDao.delete(category);
		
	}

	public void update(Category category) {
		this.categoryDao.update(category);
	}

	
	
}
