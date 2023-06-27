package com.te.practice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerDto {

	@NotBlank
	private String customerName;
	@NotEmpty
	private String products;
	@NotNull
	private Double price;
	@Email
	private String emailId;
	@NotEmpty
	private String phoneNumber;
	@NotEmpty
	private String message;

}
