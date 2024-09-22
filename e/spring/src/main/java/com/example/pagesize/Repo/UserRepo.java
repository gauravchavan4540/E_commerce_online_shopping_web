package com.example.pagesize.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pagesize.modul.User;


public interface UserRepo extends JpaRepository<User, Integer> {

	int countByUserName(String username);

	User findByUserName(String username);
}