package com.te.excel_database.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.te.excel_database.entity.Product;

public interface ProductService {

	List<Product> getData();

	void savaData(MultipartFile file);

}
