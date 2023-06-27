package com.te.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeDto {

	@NotBlank
	private String name;
	@Email
	private String emailId;
	private LocalDate joiningDate;
	@NonNull
	private Double salary;

	private List<ProjectsDto> projectsDto = new ArrayList<ProjectsDto>();

	private DepartmentDto departmentDto;

	private CompanyDto companyDto;

}
