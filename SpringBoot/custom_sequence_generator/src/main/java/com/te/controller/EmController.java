package com.te.controller;

import javax.persistence.PostLoad;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.entity.Employee;
import com.te.service.EmService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/public")
public class EmController {

	private final EmService emService;

	@PostMapping(path = "/registration")
	public void registration(@RequestBody Employee employee) {
		emService.empRegistration(employee);
	}

	@GetMapping(path = "/excelSheet")
	public void generateExcelSheet(HttpServletResponse response) {
		// response.setContentType("application/octet-stream");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=custon.xls";

		response.setHeader(headerKey, headerValue);

		emService.generateExcelSheet(response);

	}

}
