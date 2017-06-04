package com.sleep.shop.category.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sleep.shop.category.po.Category;
import com.sleep.shop.category.service.CategoryService;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{
	//模型驱动使用的类
	private Category category;
	private CategoryService	categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public Category getModel() {
		if(category == null){
			category = new Category();
		}
		return category;
	}
		
	//后台查询所有一级分类的方法
	public String findAll(){
		List<Category> categorylist = this.categoryService.findAll();
		ActionContext.getContext().getValueStack().set("categorylist", categorylist);
		return "findAll";
	}
	//添加一级分类
	public String save(){
		this.categoryService.save(category);
		return "saveSuccess";
	}
	//删除一级分类
	@SuppressWarnings("unused")
	public String delete(){
		Category newcategory = this.categoryService.findByCid(category.getCid());
		this.categoryService.delete(category);
		return "deleteSuccess";
	}

	//修改一级分类跳转
	public String edit(){
		category = this.categoryService.findByCid(category.getCid());
		return "editSuccess";
	}
	//修改一级分类
	public String update(){
		this.categoryService.update(category);
		return "updateSuccess";
	}
}
