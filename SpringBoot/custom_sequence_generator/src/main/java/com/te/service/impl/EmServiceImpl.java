package com.te.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import com.te.entity.Employee;
import com.te.repository.EmRepository;
import com.te.service.EmService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmServiceImpl implements EmService {

	private final EmRepository emRepository;

	@Override
	public void empRegistration(Employee employee) {
		emRepository.save(employee);
	}

	@Override
	public void generateExcelSheet(HttpServletResponse response) {
		List<Employee> employee = emRepository.findAll();
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		SXSSFSheet sheet = workbook.createSheet("custom sequence");
		SXSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("Id");
		row.createCell(1).setCellValue("Name");
		row.createCell(2).setCellValue("age");

		int rowSize = 1;
		for (Employee employee2 : employee) {
			SXSSFRow dataRow = sheet.createRow(rowSize);
			dataRow.createCell(0).setCellValue(employee2.getId());
			dataRow.createCell(1).setCellValue(employee2.getName());
			dataRow.createCell(2).setCellValue(employee2.getAge());
			rowSize++;
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
