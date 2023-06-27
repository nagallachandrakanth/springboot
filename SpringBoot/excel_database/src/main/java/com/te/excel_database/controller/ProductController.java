package com.te.excel_database.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.te.excel_database.entity.Helper;
import com.te.excel_database.entity.Product;
import com.te.excel_database.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping(path = "/public")
public class ProductController {
	private final ProductService productService;

	@PostMapping(path = "/product/upload")
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
		if (Helper.checkExcelFromat(file)) {
			this.productService.savaData(file);
			return ResponseEntity.ok().body("File Uploaded successfull and Data Store In The Database");
		}
		return ResponseEntity.badRequest().body("File Uploaded Unsuccessfull");

	}

	@GetMapping(path = "/getData")
	public void getData() {
		List<Product> products = productService.getData();
		if (products != null) {
			ResponseEntity.ok().body(products);
		}
		throw new RuntimeException("No Data Found In The Database");
	}
	
	

}
