package com.sleep.shop.categorysecond.po;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.sleep.shop.category.po.Category;
import com.sleep.shop.product.po.Product;

/*
 * 二级分类的实体
 */
@Entity
@Table(name="categorysecond")
public class CategorySecond {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer csid;
	private String csname;
	//所属一级分类
	@ManyToOne(targetEntity=Category.class)
	@JoinColumn(name="cid",referencedColumnName="cid")
	private Category category;
	@OneToMany(targetEntity=Product.class,mappedBy="categorysecond",fetch=FetchType.EAGER)
	private Set<Product> product = new HashSet<>();
	
	public Set<Product> getProduct() {
		return product;
	}
	public void setProduct(Set<Product> product) {
		this.product = product;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	
	
}
