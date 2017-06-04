package com.sleep.shop.product.po;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sleep.shop.categorysecond.po.CategorySecond;
import com.sleep.shop.order.po.OrderItem;

@Entity
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pid;
	private String pname;
	private double market_price;
	private double shop_price;
	private String image;
	private String pdesc;
	private int is_hot;
	private Date pdate;
	//二级分类的外键
	@ManyToOne(targetEntity=CategorySecond.class)
	@JoinColumn(name="csid",referencedColumnName="csid")
	private CategorySecond categorysecond;
	@OneToMany(targetEntity=OrderItem.class,mappedBy="product")
	private Set<OrderItem> orderItem = new HashSet<>();
	public CategorySecond getCategorysecond() {
		return categorysecond;
	}
	public void setCategorysecond(CategorySecond categorysecond) {
		this.categorysecond = categorysecond;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getMarket_price() {
		return market_price;
	}
	public void setMarket_price(double market_price) {
		this.market_price = market_price;
	}
	public double getShop_price() {
		return shop_price;
	}
	public void setShop_price(double shop_price) {
		this.shop_price = shop_price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public int getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(int is_hot) {
		this.is_hot = is_hot;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	
}
