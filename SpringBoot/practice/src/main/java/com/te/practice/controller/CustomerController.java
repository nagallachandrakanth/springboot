package com.te.practice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.te.practice.customexception.RegistrationUnSuccessfullException;
import com.te.practice.dto.CustomerDto;
import com.te.practice.entity.Helper;
import com.te.practice.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/public")
public class CustomerController {
	private final CustomerService customerService;

	@PostMapping(path = "/customerRegistration")
	public ResponseEntity<String> customerRegistration(@Valid @RequestBody CustomerDto customerDto) {
		Boolean registration = customerService.custRegistration(customerDto).get();
		if (registration) {
			return ResponseEntity.ok().body("Registration Successfull");
		}
		throw new RegistrationUnSuccessfullException("Registration UnSuccessfull");
	}

	@GetMapping(path = "/generateExcelSheet")
	public void generateExcelSheet(HttpServletResponse response) {
		response.setContentType("application/octet-stream");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment;fileName=customer.xls";
		response.setHeader(headerKey, headerValue);
		customerService.generateExcelSheet(response);

	}

	@PostMapping(path = "/excelToDatabase")
	public void bulkDataByUsingExcelSheet(@RequestParam(name = "file") MultipartFile file) {
		if (Helper.checkFromatType(file)) {
			customerService.saveData(file);
		}
	}

	@Converter(autoApply = true) 
	public class ListToStringConverter implements  AttributeConverter<List<String>, String>{
	  
	  @Override 
	  public String convertToDatabaseColumn(List<String> attribute) {
	  return Joiner.on(',').join(attribute); 
	  }
	  
	  @Override 
	  public List<String> convertToEntityAttribute(String dbData) {
	  return new ArrayList<String>(Arrays.asList(dbData.split(","))); }
	}

}
