package com.te.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.practice.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
