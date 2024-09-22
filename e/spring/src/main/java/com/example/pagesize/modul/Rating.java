package com.example.pagesize.modul;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	Date date;
	int productid;
	int userid;
	int stars;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public Rating() {
		super();
	}

	public Rating(int id, Date date, int productid, int userid, int stars) {
		super();
		this.id = id;
		this.date = date;
		this.productid = productid;
		this.userid = userid;
		this.stars = stars;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", date=" + date + ", productid=" + productid + ", userid=" + userid + ", stars="
				+ stars + "]";
	}

}