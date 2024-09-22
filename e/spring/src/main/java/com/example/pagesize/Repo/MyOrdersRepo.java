package com.example.pagesize.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pagesize.modul.MyOrders;

public interface MyOrdersRepo extends JpaRepository<MyOrders, Integer> {

}
