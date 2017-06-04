package com.sleep.shop.category.po;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OrderBy;

import com.sleep.shop.categorysecond.po.CategorySecond;

@Entity
@Table(name="category")
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cid;
	private String cname;
	@OneToMany(targetEntity=CategorySecond.class,mappedBy="category",fetch=FetchType.EAGER)
	@OrderBy(clause = "csid")//对关联集合的属性进行排序
	private Set<CategorySecond> categorySecond = new HashSet<>();
	
	
	public Set<CategorySecond> getCategorySecond() {
		return categorySecond;
	}
	public void setCategorySecond(Set<CategorySecond> categorySecond) {
		this.categorySecond = categorySecond;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
}
