package com.example.pagesize.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pagesize.modul.OrderProduct;

public interface OrderProductRepo extends JpaRepository<OrderProduct, Integer> {

}
