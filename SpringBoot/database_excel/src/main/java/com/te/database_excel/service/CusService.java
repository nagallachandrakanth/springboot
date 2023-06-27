package com.te.database_excel.service;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.te.database_excel.dto.CustomerDto;

public interface CusService {

	Optional<Boolean> customerRegistration(CustomerDto customerDto);

	void generateExcel(HttpServletResponse response);

}
