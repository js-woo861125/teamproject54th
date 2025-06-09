package ks54team01.customer.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ks54team01.customer.product.domain.BenefitDetail;
import ks54team01.customer.product.domain.CustomerProduct;



@Service
public interface CustomerProductService {
	
	List<BenefitDetail> getBenefitByProduct(String productsNum);
	
	List<CustomerProduct> getProductSpecList(String productsNum);
	
	
	List<CustomerProduct> getProductDetailByProd(String productsNum);
	
	

	List<CustomerProduct> getCustomerProductList(String smallCategory);
	
	List<CustomerProduct> getCutomeProductListAll(String productsNum);
	
	CustomerProduct getLowerPriceAndMaxPeriod(String productsNum);
	
}
