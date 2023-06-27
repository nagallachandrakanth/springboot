package com.te.service;

import javax.servlet.http.HttpServletResponse;

import com.te.entity.Employee;

public interface EmService {

	void empRegistration(Employee employee);

	void generateExcelSheet(HttpServletResponse response);

}
