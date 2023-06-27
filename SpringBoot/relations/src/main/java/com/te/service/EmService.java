package com.te.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.te.dto.EmployeeDto;

public interface EmService {

	Optional<Boolean> empRegistration(@Valid EmployeeDto employeeDto);

	Optional<Boolean> registrationByUsingStreams(EmployeeDto employeeDto);

	Optional<Boolean> updateData(String name, List<EmployeeDto> employeeDto);

}
