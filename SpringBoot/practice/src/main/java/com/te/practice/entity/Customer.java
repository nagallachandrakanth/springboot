package com.te.practice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "cus_seq")
	@GenericGenerator(name = "cus_seq",strategy = "com.te.practice.entity.StringPrefixPredefinedSequenceIdGenerator",parameters = {
			@Parameter(name=StringPrefixPredefinedSequenceIdGenerator.INCREMENT_PARAM,value = "50"),
			@Parameter(name=StringPrefixPredefinedSequenceIdGenerator.VALUE_PREFIX_PARAMETER,value = "TYC_"),
			@Parameter(name = StringPrefixPredefinedSequenceIdGenerator.NUMBER_FORMAT_PERAMETER,value = "%05d")
	})
	private String customerID;
	private String customerName;
	private String products;
	private String emailId;
	private String phoneNumber;
	private Double price;
	private String message;

}
