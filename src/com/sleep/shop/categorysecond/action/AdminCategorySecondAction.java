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
	//ģ������
	private CategorySecond categorySecond;
	//���ܷ�ҳ��ҳ��
	private int page;
	//ע��һ�������Service
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

	//�������еĶ�������Ĳ˵�
	public String findAll(){
		PageBean<CategorySecond> pagebean = this.categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pagebean", pagebean);
		return "findAll";
	}
	//��ת���ҳ��
	public String addPage(){
		//��ѯ���е�һ������
		List<Category> categorylist = categoryService.findAll();
		//��������ʾ��ҳ����
		ActionContext.getContext().getValueStack().set("categorylist", categorylist);
		
		return "addPageSuccess";
	}
	//�����������ķ���
	public String save(){
		this.categorySecondService.save(categorySecond);
		return "savesuccess";
	}
	//ɾ����������ķ���
	public String delete(){
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deletesuccess";
	}
	//�޸Ķ�������ķ���
	public String edit(){
		//����csid��ѯ����
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		//��ѯ���е�һ������
		List<Category> categorylist = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("categorylist", categorylist);
		return "editSuccess";
	}
	//���¶�������
	public String update(){
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
}
