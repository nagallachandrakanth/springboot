package com.te.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.entity.Employee;

@Repository
public interface EmpRepository extends JpaRepository<Employee, Integer> {

}
