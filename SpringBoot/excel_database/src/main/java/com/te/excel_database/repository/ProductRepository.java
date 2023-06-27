package com.te.excel_database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.excel_database.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
