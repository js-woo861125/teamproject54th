package ks54team01.customer.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.product.domain.CustomerProduct;

@Mapper
public interface ProductMapper {
	
	//상품목록 조회
	List<CustomerProduct> getCustomerProductList(String smallCategory);
			
	// 상품 상세 정보 조회 (productName으로)
	
	CustomerProduct getProductDetail(String productName);
	
}
