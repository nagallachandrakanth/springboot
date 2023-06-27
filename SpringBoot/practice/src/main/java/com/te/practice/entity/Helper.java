package com.te.practice.entity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class Helper {

	public static boolean checkFromatType(MultipartFile file) {
		String contentType = file.getContentType();
		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;
		}
	}

	public List<Customer> convertExcelToListOfProducts(InputStream file) throws IOException {
		List<Customer> customers = new ArrayList<Customer>();

		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("data");
		int rowNumber = 0;
		Iterator<Row> iterator = sheet.iterator();
		while (iterator.hasNext()) {
			Row row = iterator.next();
			if (rowNumber == 0) {
				rowNumber++;
				continue;
			}
			Iterator<Cell> cells = row.iterator();
			int cellNumber = 0;
			Customer customer = new Customer();
			while (cells.hasNext()) {
				Cell cell = cells.next();
				switch (cellNumber) {
				case 0:
					customer.setCustomerID(cell.getStringCellValue());

					break;
				case 1:
					customer.setCustomerName(cell.getStringCellValue());

					break;
				case 2:
					customer.setProducts(cell.getStringCellValue());

					break;
				case 3:
					customer.setEmailId(cell.getStringCellValue());

					break;
				case 4:
					customer.setPhoneNumber(cell.getStringCellValue());

					break;
				case 5:
					customer.setPrice(cell.getNumericCellValue());

					break;
				case 6:
					customer.setMessage(cell.getStringCellValue());

					break;

				default:
					break;
				}
				cellNumber++;
				customers.add(customer);

			}
		}
		return customers;

	}
}
