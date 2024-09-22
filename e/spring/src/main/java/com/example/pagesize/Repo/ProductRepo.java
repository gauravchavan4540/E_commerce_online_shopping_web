package com.example.pagesize.Repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.pagesize.Projection.ProductBuyerUI;
import com.example.pagesize.Projection.ProductUI;
import com.example.pagesize.modul.Product;


public interface ProductRepo extends JpaRepository<Product, Integer> {
	List<Product> findByUserid(int id);

	@Query(value = "SELECT p.name,p.price,p.quantity,p.discount,p.description,c.cat_name as catName FROM product p join category c on p.categoryid=c.id where p.userid=?1", nativeQuery = true)
	List<ProductUI> findByCategoryName(int userId);

	@Query(value = "select id,description,discount,name,price,quantity,rating,datediff(now(),date) as days \r\n"
			+ "from product where categoryid=?1 and price>?2 and price <?3 and rating=?4", nativeQuery = true)
	List<ProductBuyerUI> findProductByFilter(int categoryid, int minprice, int maxprice, double rating);

}