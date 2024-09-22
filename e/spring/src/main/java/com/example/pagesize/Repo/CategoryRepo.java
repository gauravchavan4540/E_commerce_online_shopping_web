package com.example.pagesize.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pagesize.modul.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
