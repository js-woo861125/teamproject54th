package ks54team01.customer.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.product.domain.CustomerProduct;

@Mapper
public interface ProductMapper {
	
	
	List<CustomerProduct> getProductDetailByProd(String productsNum);
	
	
	//상품목록 조회
	List<CustomerProduct> getCustomerProductList(String smallCategory);
			
	
}
