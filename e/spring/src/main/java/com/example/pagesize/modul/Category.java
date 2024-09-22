package com.example.pagesize.modul;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String catName;
	Date date;
	int userId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return catName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setName(String name) {
		this.catName = name;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Category(int id, String name, Date date, int userId) {
		super();
		this.id = id;
		this.catName = name;
		this.date = date;
		this.userId = userId;
	}

	public Category() {
		super();
	}

	@Override
	public String toString() {
		return "Catagory [id=" + id + ", name=" + catName + ", date=" + date + ", userId=" + userId + "]";
	}

}