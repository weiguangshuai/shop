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
	//�ϴ��ļ�����Ĳ���
	private File upload;//�ϴ����ļ�
	private String uploadFileName;//�ϴ����ļ���
	private String uploadContentType;//�ϴ����ļ�����
	
	
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
	//ģ������
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
	//ע��service
	private ProductService productService;
	private CategorySecondService categorySecondService;
	
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	//��ʾ���е���Ʒ
	public String findAll(){
		PageBean<Product> pagebean = productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pagebean", pagebean);
		return "findAllsuccess";
		}
	//��ת�����ҳ��ķ���
	public String addPage(){
		//��ѯ���еĶ������༯��
		List<CategorySecond> cslist = categorySecondService.findAll();
		//���浽ֵջ��
		ActionContext.getContext().getValueStack().set("cslist", cslist);
		return "addPagesuccess";
	}
	//�����Ʒ
	public String save() throws IOException{
		if(upload != null){
			//��þ���·��
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
			 * ���ù������ϴ��ļ�
		//����һ���ļ�
			File diskfile = new File(realpath+"//"+uploadFileName);
			//�ļ��ϴ�
			FileUtils.copyFile(upload, diskfile);
			*/
			product.setImage("products/"+uploadFileName);
		}
		product.setPdate(new Date());
		productService.save(product);
		return "savesuccess";
	}
	//ɾ����Ʒ
	public String delete(){
		//�Ȳ�ѯ����ɾ��
		product = productService.findByPid(product.getPid());
		//ɾ����Ʒ
		productService.delete(product);
		//ɾ����Ʒ�ϴ���ͼƬ
		String path = product.getImage();
		if(path != null){
			String realpath = "D:\\workbench\\Java web\\shop\\WebContent"+"//"+path;
			File file = new File(realpath);
			file.delete();
		}
		return "deletesuccess";
	}
	//�޸���Ʒ��Ϣҳ����ת
	public String edit(){
		//������Ʒid��ѯ��Ʒ
		product = productService.findByPid(product.getPid());
		//��ѯ���ж�������ļ���
		List<CategorySecond> cslist = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("cslist", cslist);
		//ҳ����ת
		return "editsuccess";
	}
	//�޸���Ʒ�ķ���
	public String update() throws IOException{
		product.setPdate(new Date());
		//�ϴ��ļ�
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
