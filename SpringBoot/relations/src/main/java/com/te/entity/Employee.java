package com.te.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String emailId;
	private LocalDate joiningDate;
	private Double salary;

	@ManyToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<Projects> projects = new ArrayList<Projects>();

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Department department;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Company company;

}
