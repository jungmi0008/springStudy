package com.product.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.product.dto.ProductDTO;
import com.product.dao.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService {

	@Inject
	ProductDAO productDao;
	
	//상품 목록
	@Override
	public List<ProductDTO> listProduct() {
		return productDao.listProduct();
	}

	//상품 상세 정보
	@Override
	public ProductDTO detailProduct(int product_id) {
		return productDao.detailProduct(product_id);
	}

}





