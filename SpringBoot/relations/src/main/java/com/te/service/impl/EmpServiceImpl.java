package com.te.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.te.dto.EmployeeDto;
import com.te.dto.ProjectsDto;
import com.te.entity.Company;
import com.te.entity.Department;
import com.te.entity.Employee;
import com.te.entity.Projects;
import com.te.repository.ComRepository;
import com.te.repository.EmpRepository;
import com.te.service.EmService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmService {
	private final EmpRepository empRepository;
	private final JavaMailSender javaMailSender;
	private final ModelMapper modelMapper;
	private final ComRepository comRepository;

	@Override
	@Transactional
	public Optional<Boolean> empRegistration(@Valid EmployeeDto employeeDto) {
		if (employeeDto != null) {
			Employee employee = new Employee();
			BeanUtils.copyProperties(employeeDto, employee);
			Department department = new Department();
			BeanUtils.copyProperties(employeeDto.getDepartmentDto(), department);
			employee.setDepartment(department);
			department.getEmployee().add(employee);
			Company company = new Company();
			BeanUtils.copyProperties(employeeDto.getCompanyDto(), company);
			employee.setCompany(company);
			company.getEmployee().add(employee);
			List<Projects> projects = new ArrayList<Projects>();
			for (ProjectsDto projectsDto : employeeDto.getProjectsDto()) {
				Projects projectsEntity = new Projects();
				BeanUtils.copyProperties(projectsDto, projectsEntity);
				projects.add(projectsEntity);
				employee.getProjects().addAll(projects);
				projectsEntity.getEmployee().add(employee);
			}

			empRepository.save(employee);

			try {
				mailSending(employeeDto.getEmailId(), "Testing the mail", "passworddd\n",
						"C:\\Users\\CHANDRAKANTH\\Downloads\\epfo.pdf");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Email Sending Successfull");

			return Optional.ofNullable(true);

		}
		return Optional.ofNullable(false);

	}

	public void mailSending(String to, String subject, String text, String attachment) throws MessagingException {
		MimeMessage createMimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(createMimeMessage, true);
		messageHelper.setFrom("chandramailsender@gmail.com");
		messageHelper.setTo(to);
		messageHelper.setSubject(subject);
		messageHelper.setText(text);
		FileSystemResource fileSystemResource = new FileSystemResource(attachment);
		messageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
		javaMailSender.send(createMimeMessage);
	}

	@Override
	public Optional<Boolean> registrationByUsingStreams(EmployeeDto employeeDto) {
		if (employeeDto != null) {
			Employee employee = modelMapper.map(employeeDto, Employee.class);
			employee.getProjects().stream().forEach(e -> e.getEmployee().add(employee));

			empRepository.save(employee);
			return Optional.ofNullable(true);
		}
		return Optional.ofNullable(false);

	}

	@Override
	public Optional<Boolean> updateData(String name, List<EmployeeDto> employeeDto) {
		Company company = comRepository.findByCompanyName(name).get();
		if (company != null) {
			for (EmployeeDto employeeDto2 : employeeDto) {
				BeanUtils.copyProperties(employeeDto2,
						company.getEmployee().stream().filter(e -> e.getName().equalsIgnoreCase(employeeDto2.getName()))
								.collect(Collectors.toList()).get(0),
						new String[] { "id", "company" });
			}
			comRepository.save(company);
			return Optional.ofNullable(true);

		}
		return Optional.ofNullable(false);

	}

}
