package com.te.practice.service;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.te.practice.dto.CustomerDto;

public interface CustomerService {

	Optional<Boolean> custRegistration(CustomerDto customerDto);

	void generateExcelSheet(HttpServletResponse response);

	void saveData(MultipartFile file);

}
