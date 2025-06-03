package ks54team01.customer.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	

    
	
	@Override
	public CustomerProduct getLowerPriceAndMaxPeriod(String productsNum) {
		
		return productMapper.getLowerPriceAndMaxPeriod(productsNum);
	}
	
	
	@Override
	public List<CustomerProduct> getProductSpecList(String productsNum) {
		
		List<CustomerProduct> productSpecList = productMapper.getProductSpecList(productsNum);
		
		return productSpecList;
	}
	
	
	// 상품 상세 화면
	@Override
	public List<CustomerProduct> getProductDetailByProd(String productsNum) {
		
		List<CustomerProduct> productDetailByProd = productMapper.getProductDetailByProd(productsNum);
		
		return productDetailByProd;
	}
	
	
	//	상품 목록조회
		@Override
		public List<CustomerProduct> getCustomerProductList(String smallCategory) {
		
		List<CustomerProduct> customerProductList = productMapper.getCustomerProductList(smallCategory);
		
		return customerProductList;
	}
	

}
