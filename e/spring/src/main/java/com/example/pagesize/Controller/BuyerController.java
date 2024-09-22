package com.example.pagesize.Controller;


import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pagesize.Projection.ProductBuyerUI;
import com.example.pagesize.Repo.CartRepo;
import com.example.pagesize.Repo.MyOrdersRepo;
import com.example.pagesize.Repo.OrderProductRepo;
import com.example.pagesize.Repo.ProductRepo;
import com.example.pagesize.Repo.RatingRepo;
import com.example.pagesize.modul.Cart;
import com.example.pagesize.modul.MyOrders;
import com.example.pagesize.modul.OrderProduct;
import com.example.pagesize.modul.Product;
import com.example.pagesize.modul.Rating;


@RestController
@CrossOrigin//(origins = { "http://localhost:4200", "http://3.93.19.80:8080/amazon-clone" })
@RequestMapping("buyer")
public class BuyerController {
	@Autowired
	ProductRepo productRepo;

	@Autowired
	RatingRepo ratingRepo;

	@Autowired
	OrderProductRepo orderProductRepo;

	@Autowired
	MyOrdersRepo myOrdersRepo;

	@Autowired
	CartRepo cartRepo;

	@DeleteMapping("removeFromCart/{productid}/{userid}")
	public int removeFromCart(@PathVariable int productid, @PathVariable int userid) {
		try {
			List<Cart> cartItems = cartRepo.findByProductidAndUserid(productid, userid);
			if (!cartItems.isEmpty()) {
				cartRepo.delete(cartItems.get(0)); // Remove the first match (assuming unique product per user)
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@RequestMapping("getCartProducts/{userid}")
	public List<Product> getCartProducts(@PathVariable int userid) {
		List<Cart> cartItems = cartRepo.findByUserid(userid);
		int count = cartRepo.countByUserid(userid);
		if (count == 0)
			return null;
		else {
			List<Integer> productIds = cartItems.stream().map(Cart::getProductid).collect(Collectors.toList());
			return productRepo.findAllById(productIds);
		}
	}

	@RequestMapping("addToCart/{productid}/{userid}")
	public int addToCart(@PathVariable int productid, @PathVariable int userid) {
		try {
			int count = cartRepo.countByProductidAndUserid(productid, userid);
			if (count == 1)
				return 1;
			else if (count > 1)
				return 0;
			else {
				Cart cart = new Cart();
				cart.setProductid(productid);
				cart.setUserid(userid);
//				cart.setQuantity(quantity);
				cartRepo.save(cart);
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;// exception on server
		}
	}

//	@RequestMapping("giveRatingToProduct/{productid}/{userid}/{rating}")
//	public int getRating(@PathVariable int productid, @PathVariable int userid, @PathVariable int rating) {
//		try {
//			int count = ratingRepo.countByProductidAndUserid(productid, userid);
//			if (count == 1) {
//				Rating newRating = ratingRepo.findByProductidAndUserid(productid, userid);
//				newRating.setStars((int)(newRating.getStars() + rating) / 2);
//				newRating.setDate(new Date());
//				ratingRepo.save(newRating);
//			} else if (count == 0) {
//				Rating newrating = new Rating();
//				newrating.setProductid(productid);
//				newrating.setStars(rating);
//				newrating.setUserid(userid);
//				newrating.setDate(new Date());
//				ratingRepo.save(newrating);
//				return 2;
//			} else
//				return 0;
//
//			double avg = ratingRepo.getAvgRatingByProductId(productid);
//
////			Product sellerAddedProducts = productRepo.findById(productid).get();
////			sellerAddedProducts.setRating(avg);
////			productRepo.save(sellerAddedProducts);
////			return 1;
//
//			Product product = productRepo.findById(productid).get();
//			product.setRating(avg);
//			productRepo.save(product);
//			return 1;
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return 0;
//		}
//	}
	@RequestMapping("giveRatingToProduct/{productid}/{userid}/{rating}")
	public int giveRatingToProduct(@PathVariable int productid, @PathVariable int userid, @PathVariable int rating) {
		try {
			// Check if the user has already rated the product
			Rating existingRating = ratingRepo.findByProductidAndUserid(productid, userid);
			System.out.println(rating);
			if (existingRating != null) {
				// Update the existing rating
				existingRating.setStars(rating);
				existingRating.setDate(new Date());
				ratingRepo.save(existingRating);
			} else {
				// Add a new rating
				Rating newRating = new Rating();
				newRating.setProductid(productid);
				newRating.setStars(rating);
				newRating.setUserid(userid);
				newRating.setDate(new Date());
				ratingRepo.save(newRating);
			}

			// Recalculate the average rating
			updateProductRating(productid);

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	private void updateProductRating(int productid) {
		// Fetch all ratings for the product
		List<Rating> ratings = ratingRepo.findByProductid(productid);

		if (ratings.isEmpty()) {
			// Handle case where there are no ratings
			Product product = productRepo.findById(productid).orElse(null);
			if (product != null) {
				product.setRating(0); // No ratings yet
				productRepo.save(product);
			}
			return;
		}

		// Calculate the average rating
		double avgRating = ratings.stream().mapToInt(Rating::getStars).average().orElse(0);

		// Update the product with the new average rating
		Product product = productRepo.findById(productid).orElse(null);
		if (product != null) {
			product.setRating((int) avgRating);
			productRepo.save(product);
		}
	}

	@RequestMapping("getProductsBuyer")
	public List<ProductBuyerUI> getProductsBuyer(@RequestBody int[] a) {
		return productRepo.findProductByFilter(a[0], a[1], a[2], a[3]);
	}
	@RequestMapping("getAllProducts")
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}
	/*
	 * @PostMapping("onPlaceOrder/{id}") public int placeOrder(@PathVariable("id")
	 * int id, @RequestBody int[][] a) { try { MyOrders myOrders = new MyOrders();
	 * myOrders.setDate(new Date()); myOrders.setUserid(id); myOrders =
	 * myOrdersRepo.save(myOrders); double totalamount = 0;
	 * 
	 * for (int[] a1 : a) { int cartid = a1[0]; int quantity = a1[1];
	 * 
	 * Cart cart = cartRepo.findById(cartid) .orElseThrow(() -> new
	 * RuntimeException("Cart not found: " + cartid)); int prodid =
	 * cart.getProductid();
	 * 
	 * Product sellerAddedProducts = productRepo.findById(prodid) .orElseThrow(() ->
	 * new RuntimeException("Product not found: " + prodid)); OrderProduct obj = new
	 * OrderProduct(); double amount = sellerAddedProducts.getPrice() * quantity;
	 * amount = amount - (amount * sellerAddedProducts.getDiscount() / 100);
	 * obj.setAmount(amount); totalamount += amount;
	 * 
	 * obj.setDate(new Date()); obj.setOrderid(myOrders.getId());
	 * obj.setProductid(prodid);
	 * 
	 * orderProductRepo.save(obj);
	 * 
	 * cartRepo.delete(cart); }
	 * 
	 * myOrders.setAmount(totalamount); myOrdersRepo.save(myOrders);
	 * 
	 * return 1;
	 * 
	 * } catch (Exception e) { e.printStackTrace(); return -1; } }
	 */

	@PostMapping("placeOrder/{userid}")
	public int placeOrder(@PathVariable int userid, @RequestBody int[] cartIds) {
		try {
			MyOrders myOrders = new MyOrders();
			myOrders.setDate(new Date());
			myOrders.setUserid(userid);
			myOrders = myOrdersRepo.save(myOrders);

			double totalAmount = 0;

			for (int cartId : cartIds) {
				Cart cart = cartRepo.findById(cartId).orElse(null);
				if (cart != null) {
					int productId = cart.getProductid();
					Product product = productRepo.findById(productId).orElse(null);

					if (product != null) {
						OrderProduct orderProduct = new OrderProduct();
						double amount = product.getPrice();
						amount -= (amount * product.getDiscount() / 100);
						orderProduct.setAmount(amount);
						totalAmount += amount;
						orderProduct.setDate(new Date());
						orderProduct.setOrderid(myOrders.getId());
						orderProduct.setProductid(productId);
						orderProductRepo.save(orderProduct);
					}
					// cartRepo.delete(cart);
				}
			}

			myOrders.setAmount(totalAmount);
			myOrdersRepo.save(myOrders);
			cartRepo.deleteAll();

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}