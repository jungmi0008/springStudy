package com.product.service;

import java.util.List;

import com.product.dto.ProductDTO;

public interface ProductService {

	List<ProductDTO> listProduct();
	
	ProductDTO detailProduct(int product_id);
	
}















