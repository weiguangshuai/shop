package com.sleep.shop.categorysecond.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sleep.shop.category.po.Category;
import com.sleep.shop.category.service.CategoryService;
import com.sleep.shop.categorysecond.po.CategorySecond;
import com.sleep.shop.categorysecond.service.CategorySecondService;
import com.sleep.shop.util.PageBean;

public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
	//模型驱动
	private CategorySecond categorySecond;
	//接受分页的页数
	private int page;
	//注入一级分类的Service
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	private CategorySecondService categorySecondService;
	
	public CategorySecondService getCategorySecondService() {
		return categorySecondService;
	}

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
}

	public CategorySecond getModel() {
		if(categorySecond == null){
			categorySecond = new CategorySecond();
		}
		return categorySecond;
	}

	//查找所有的二级分类的菜单
	public String findAll(){
		PageBean<CategorySecond> pagebean = this.categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pagebean", pagebean);
		return "findAll";
	}
	//跳转添加页面
	public String addPage(){
		//查询所有的一级分类
		List<Category> categorylist = categoryService.findAll();
		//把数据显示到页面中
		ActionContext.getContext().getValueStack().set("categorylist", categorylist);
		
		return "addPageSuccess";
	}
	//保存二级分类的方法
	public String save(){
		this.categorySecondService.save(categorySecond);
		return "savesuccess";
	}
	//删除二级分类的方法
	public String delete(){
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deletesuccess";
	}
	//修改二级分类的方法
	public String edit(){
		//更具csid查询数据
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		//查询所有的一级分类
		List<Category> categorylist = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("categorylist", categorylist);
		return "editSuccess";
	}
	//更新二级分类
	public String update(){
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
}
