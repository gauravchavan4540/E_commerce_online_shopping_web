package com.example.pagesize.Controller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pagesize.Projection.ProductUI;
import com.example.pagesize.Repo.ProductRepo;
import com.example.pagesize.modul.Product;


@RestController
@CrossOrigin  //(origins = { "http://localhost:4200","http://3.93.19.80:8080/amazon"})
@RequestMapping("seller")
public class SellerController {

	@Autowired
	ProductRepo productRepo;

//	@RequestMapping("getAllProducts{userid}")
//	public List<Product> getAllProducts(@PathVariable int userid) {
//		return productRepo.findByUserid(userid);
//	}

	@RequestMapping("getAllProducts{userid}")
	public List<ProductUI> findByCategoryName(@PathVariable int userid) {
		return productRepo.findByCategoryName(userid);
	}

	@RequestMapping("addNewProduct")
	public Product addNewProduct(@RequestBody Product obj) {
		obj.setDate(new Date());
		return productRepo.save(obj);
	}
}