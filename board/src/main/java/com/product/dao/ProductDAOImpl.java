package com.product.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.product.dto.ProductDTO;

@Repository // 스프링에서 자동으로 생성(관리)하게 하기 위해서 사용한다.
public class ProductDAOImpl implements ProductDAO {

	@Inject // 의존 관계 주입
	SqlSession sqlSession;
	
	// 상품 목록
	@Override
	public List<ProductDTO> listProduct() {
		return sqlSession.selectList("product.product_list");
	}

	// 상품 상세 정보
	@Override
	public ProductDTO detailProduct(int product_id) {
		return sqlSession.selectOne("product.detail_product", product_id);
	}

}








