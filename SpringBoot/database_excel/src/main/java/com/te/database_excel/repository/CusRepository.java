package com.te.database_excel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.database_excel.entity.Customer;

@Repository
public interface CusRepository extends JpaRepository<Customer, String> {

}
