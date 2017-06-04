package com.sleep.shop.order.po;
//CREATE TABLE `order` (
//		  `oid` int(11) NOT NULL AUTO_INCREMENT,
//		  `total` double DEFAULT NULL,
//		  `ordertime` datetime DEFAULT NULL,
//		  `state` int(11) DEFAULT NULL,
//		  `name` varchar(20) DEFAULT NULL,
//		  `phone` varchar(20) DEFAULT NULL,
//		  `addr` varchar(50) DEFAULT NULL,
//		  `uid` int(11) DEFAULT NULL,
//		  PRIMARY KEY (`oid`),
//		  KEY `FKC3DF62E5AA3D9C7` (`uid`),
//		  CONSTRAINT `FKC3DF62E5AA3D9C7` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
//		) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
import java.util.Date;
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

import com.sleep.shop.user.po.User;
@Entity
@Table(name="orders")
public class Orders {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer oid;
	private double total;
	private Date ordertime;
	private Integer state;
	private String name;
	private String phone;
	private String address;
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="uid",referencedColumnName="uid")
	private User user;
	@OneToMany(targetEntity=OrderItem.class,cascade=CascadeType.ALL,mappedBy="orders",fetch=FetchType.EAGER)
	private Set<OrderItem> orderItems = new HashSet<>();

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	
}
