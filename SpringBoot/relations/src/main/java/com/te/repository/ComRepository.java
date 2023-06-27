package com.te.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.entity.Company;

@Repository
public interface ComRepository extends JpaRepository<Company, Integer> {

	Optional<Company> findByCompanyName(String name);

}
