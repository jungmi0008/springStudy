package com.product.dao;

import java.util.List;

import com.product.dto.ProductDTO;

public interface ProductDAO {
	List<ProductDTO> listProduct();
	ProductDTO detailProduct(int product_id);
}
