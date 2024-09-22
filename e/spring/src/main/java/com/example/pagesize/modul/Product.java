package com.example.pagesize.modul;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name; // ui user
	Date date;
	int userid; // ui from app
	int price; // ui user
	int quantity; // ui user
	double rating;
	String description; // uiuser
	double discount; // % stage discount
	int categoryid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", date=" + date + ", userid=" + userid + ", price=" + price
				+ ", quantity=" + quantity + ", rating=" + rating + ", description=" + description + ", discount="
				+ discount + ", categoryid=" + categoryid + "]";
	}

	public Product() {
		super();
	}

	public Product(int id, String name, Date date, int userid, int price, int quantity, double rating,
			String description, double discount, int categoryid) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.userid = userid;
		this.price = price;
		this.quantity = quantity;
		this.rating = rating;
		this.description = description;
		this.discount = discount;
		this.categoryid = categoryid;
	}

}