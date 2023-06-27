package com.te.database_excel.service.impl;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.te.database_excel.dto.CustomerDto;
import com.te.database_excel.entity.Customer;
import com.te.database_excel.repository.CusRepository;
import com.te.database_excel.service.CusService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CusServiceImpl implements CusService {
	private final CusRepository cusRepository;

	@Override
	public Optional<Boolean> customerRegistration(CustomerDto customerDto) {
		if (customerDto != null) {
			Customer customer = new Customer();
			BeanUtils.copyProperties(customerDto, customer);
			cusRepository.save(customer);
			return Optional.ofNullable(true);
		}
		return Optional.ofNullable(false);
	}

	public void generateExcel(HttpServletResponse response) {
		List<Customer> customer = cusRepository.findAll();
		//HSSFWorkbook workbook = new HSSFWorkbook();
		SXSSFWorkbook workbook=new SXSSFWorkbook();
		//HSSFWorkbook sheet = workbook.createSheet("Customer Info");
		SXSSFSheet sheet = workbook.createSheet("Customer Info");
		//HSSFRow row = sheet.createRow(0);
		SXSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("Id");
		row.createCell(1).setCellValue("Name");
		row.createCell(2).setCellValue("Product");
		row.createCell(3).setCellValue("Price");

		int dataRowIndex = 1;

		for (Customer customerData : customer) {
			//HSSFRow dataRow = sheet.createRow(dataRowIndex);
			SXSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(customerData.getId());
			dataRow.createCell(1).setCellValue(customerData.getName());
			dataRow.createCell(2).setCellValue(customerData.getProduct());
			dataRow.createCell(3).setCellValue(customerData.getPrice());
			dataRowIndex++;
		}
		ServletOutputStream outputStream;
		try {
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
