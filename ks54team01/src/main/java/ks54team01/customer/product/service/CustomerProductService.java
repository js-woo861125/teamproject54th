package ks54team01.customer.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ks54team01.customer.product.domain.CustomerProduct;



@Service
public interface CustomerProductService {
	
	CustomerProduct getProductDetail(String sellProductsNo);

	List<CustomerProduct> getCustomerProductList(String smallCategory);
	
	
	
}
