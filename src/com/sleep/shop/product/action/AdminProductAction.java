package com.sleep.shop.product.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sleep.shop.categorysecond.po.CategorySecond;
import com.sleep.shop.categorysecond.service.CategorySecondService;
import com.sleep.shop.product.po.Product;
import com.sleep.shop.product.service.ProductService;
import com.sleep.shop.util.PageBean;


@SuppressWarnings("serial")
public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{
	//上传文件所需的参数
	private File upload;//上传的文件
	private String uploadFileName;//上传的文件名
	private String uploadContentType;//上传的文件类型
	
	
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	//模型驱动
	private Product product;
	public Product getModel() {
		if(product == null){
			product = new Product();
		}
		return product;
	}
	private int page;
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	//注入service
	private ProductService productService;
	private CategorySecondService categorySecondService;
	
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	//显示所有的商品
	public String findAll(){
		PageBean<Product> pagebean = productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pagebean", pagebean);
		return "findAllsuccess";
		}
	//跳转到添加页面的方法
	public String addPage(){
		//查询所有的二级分类集合
		List<CategorySecond> cslist = categorySecondService.findAll();
		//报存到值栈中
		ActionContext.getContext().getValueStack().set("cslist", cslist);
		return "addPagesuccess";
	}
	//添加商品
	public String save() throws IOException{
		if(upload != null){
			//获得绝对路径
			//String realpath = ServletActionContext.getServletContext().getRealPath("/products");
			String realpath = "D:\\workbench\\Java web\\shop\\WebContent\\products";
			FileOutputStream out = new FileOutputStream(realpath+"//"+getUploadFileName());
			FileInputStream in = new FileInputStream(getUpload());
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len = in.read(buffer)) > 0){
				out.write(buffer,0,len);
			}
			/*
			 * 利用工具类上传文件
		//创建一个文件
			File diskfile = new File(realpath+"//"+uploadFileName);
			//文件上传
			FileUtils.copyFile(upload, diskfile);
			*/
			product.setImage("products/"+uploadFileName);
		}
		product.setPdate(new Date());
		productService.save(product);
		return "savesuccess";
	}
	//删除商品
	public String delete(){
		//先查询，再删除
		product = productService.findByPid(product.getPid());
		//删除商品
		productService.delete(product);
		//删除商品上传的图片
		String path = product.getImage();
		if(path != null){
			String realpath = "D:\\workbench\\Java web\\shop\\WebContent"+"//"+path;
			File file = new File(realpath);
			file.delete();
		}
		return "deletesuccess";
	}
	//修改商品信息页面跳转
	public String edit(){
		//根据商品id查询商品
		product = productService.findByPid(product.getPid());
		//查询所有二级分类的集合
		List<CategorySecond> cslist = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("cslist", cslist);
		//页面跳转
		return "editsuccess";
	}
	//修改商品的方法
	public String update() throws IOException{
		product.setPdate(new Date());
		//上传文件
		if(upload != null){
			String path = product.getImage();
			File file = new File("D:\\workbench\\Java web\\shop\\WebContent"+"//"+path);
			file.delete();
			String realpath = "D:\\workbench\\Java web\\shop\\WebContent\\products";
			FileOutputStream out = new FileOutputStream(realpath+"//"+uploadFileName);
			FileInputStream in = new FileInputStream(upload);
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len = in.read(buffer))>0){
				out.write(buffer, 0, len);
			}
			product.setImage("products/"+uploadFileName);
		}
		productService.update(product);
		return "updatesuccess";
	}
}
