package com.te.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.controller.customexception.RegistrationUnSuccessfullException;
import com.te.dto.EmployeeDto;
import com.te.service.EmService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/public")
@RequiredArgsConstructor
public class EmController {
	private final EmService emService;

	@PostMapping(path = "/employeeRegistration")
	public ResponseEntity<String> registration(@Valid @RequestBody EmployeeDto employeeDto) {
		Optional<Boolean> registration = emService.empRegistration(employeeDto);
		if (registration.get()) {
			return ResponseEntity.ok().body("Registration Successfull");
		}
		throw new RegistrationUnSuccessfullException("Registration Unsuccessfull");

	}

	@PostMapping(path = "/registrationByUsingStreams")
	public ResponseEntity<String> registrationByUsingStreams(@RequestBody EmployeeDto employeeDto) {
		Optional<Boolean> registrationByUsingStreams = emService.registrationByUsingStreams(employeeDto);
		if (registrationByUsingStreams.get()) {
			return ResponseEntity.ok().body("Registration Successfull");
		}
		throw new RegistrationUnSuccessfullException("Registration Unsuccessfull");

	}

	@PutMapping(path = "/updateApi/{companyName}")
	public ResponseEntity<String> updateApi(@PathVariable(name = "companyName") String name,
			@RequestBody List<EmployeeDto> employeeDto) {
		Optional<Boolean> updateData = emService.updateData(name, employeeDto);
		if (updateData.get()) {
			return ResponseEntity.ok().body("Update Data Successfull");
		}
		throw new RuntimeException("Update Data Unsuccessull");

	}

}
