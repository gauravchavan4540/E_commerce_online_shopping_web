package com.example.pagesize.Controller;


import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pagesize.Repo.CategoryRepo;
import com.example.pagesize.modul.Category;

@RestController
@CrossOrigin  //(origins = { "http://localhost:4200","http://3.93.19.80:8080/amazon"})  // Allow requests from this origin
@RequestMapping("admin")
public class AdminController {

	@Autowired
	CategoryRepo categoryRepo;

	@RequestMapping("getAllCategories")
	public List<Category> getAllCategories() {
		return categoryRepo.findAll();
	}

	@DeleteMapping("deleteCategory/{id}")
	public boolean deleteCategory(@PathVariable int id) {
		boolean status = false;
		if (id != 0) {
			status = true;
			categoryRepo.deleteById(id);
		}
		return status;
	}

	@RequestMapping("addNewCategory{userid}")
	public Category addNewCategory(@PathVariable int userid, @RequestBody String name) {
		Category c = new Category();
		c.setDate(new Date());
		c.setName(name);
		c.setUserId(userid);
		return categoryRepo.save(c);
	}
}