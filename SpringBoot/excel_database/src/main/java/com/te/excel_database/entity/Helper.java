package com.te.excel_database.entity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class Helper {

	public static Boolean checkExcelFromat(MultipartFile file) {
		String contentType = file.getContentType();
		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;
		}

	}

	public static List<Product> convertExcelToListOfProducts(InputStream file) {
		List<Product> products = new ArrayList<Product>();

		try {
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
				int cellId = 0;

				Product product = new Product();

				while (cells.hasNext()) {
					Cell cell = cells.next();
					switch (cellId) {
					case 0:
						product.setProductId(cell.getStringCellValue());
						break;
					case 1:
						product.setProductName(cell.getStringCellValue());
						break;
					case 2:
						product.setProductPrice(cell.getNumericCellValue());
						break;

					default:
						break;
					}
					cellId++;
				}
				products.add(product);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return products;

	}

}
