package com.te.database_excel.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.database_excel.dto.CustomerDto;
import com.te.database_excel.service.CusService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/public")
public class CusController {

	private final CusService cusService;

	@PostMapping(path = "/customerRegistration")
	public ResponseEntity<String> registration(@RequestBody CustomerDto customerDto) {
		Optional<Boolean> customerRegistration = cusService.customerRegistration(customerDto);
		if (customerRegistration.get()) {
			return ResponseEntity.ok().body("Registration Successfull");
		}
		return ResponseEntity.badRequest().body("Registration UnSuccessfull");

	}

	@GetMapping(path = "/excelFile")
	public void generateExcelSheet(HttpServletResponse response) {
		response.setContentType("application/octet-stream");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=customer.xls";
		response.setHeader(headerKey, headerValue);
		cusService.generateExcel(response);

	}

}
