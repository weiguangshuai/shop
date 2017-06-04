package com.sleep.shop.category.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sleep.shop.category.po.Category;
import com.sleep.shop.category.service.CategoryService;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{
	//ģ������ʹ�õ���
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
		
	//��̨��ѯ����һ������ķ���
	public String findAll(){
		List<Category> categorylist = this.categoryService.findAll();
		ActionContext.getContext().getValueStack().set("categorylist", categorylist);
		return "findAll";
	}
	//���һ������
	public String save(){
		this.categoryService.save(category);
		return "saveSuccess";
	}
	//ɾ��һ������
	@SuppressWarnings("unused")
	public String delete(){
		Category newcategory = this.categoryService.findByCid(category.getCid());
		this.categoryService.delete(category);
		return "deleteSuccess";
	}

	//�޸�һ��������ת
	public String edit(){
		category = this.categoryService.findByCid(category.getCid());
		return "editSuccess";
	}
	//�޸�һ������
	public String update(){
		this.categoryService.update(category);
		return "updateSuccess";
	}
}
