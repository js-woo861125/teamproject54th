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
	
	@Override
	public List<CustomerProduct> getCustomerProductList(String smallCategory) {
		
		List<CustomerProduct> customerProductList = productMapper.getCustomerProductList(smallCategory);
		
		return customerProductList;
	}
	
}
