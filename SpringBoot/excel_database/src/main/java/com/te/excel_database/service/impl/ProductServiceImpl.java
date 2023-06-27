package com.te.excel_database.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.te.excel_database.entity.Helper;
import com.te.excel_database.entity.Product;
import com.te.excel_database.repository.ProductRepository;
import com.te.excel_database.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;

	public void savaData(MultipartFile file) {
		try {
			List<Product> products = Helper.convertExcelToListOfProducts(file.getInputStream());
			productRepository.saveAll(products);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Product> getData() {
		return productRepository.findAll();

	}

}
