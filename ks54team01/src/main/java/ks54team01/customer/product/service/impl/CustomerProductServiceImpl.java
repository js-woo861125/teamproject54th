package ks54team01.customer.product.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.customer.product.domain.CustomerProduct;
import ks54team01.customer.product.mapper.ProductMapper;
import ks54team01.customer.product.service.CustomerProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CustomerProductServiceImpl implements CustomerProductService {

	private final ProductMapper productMapper;
	
	
	// 상품 이름으로 상품 상세 정보 조회 메서드 구현
		@Override
		public CustomerProduct getProductDetail(String sellProductsNo) {
			return productMapper.getProductDetail(sellProductsNo);
			// ProductMapper의 getProductDetail 메서드를 호출하여 상품 상세 정보 조회 후 반환
		}

	
//	상품 목록조회
	@Override
	public List<CustomerProduct> getCustomerProductList(String smallCategory) {
		
		List<CustomerProduct> customerProductList = productMapper.getCustomerProductList(smallCategory);
		
		return customerProductList;
	}
	

}
