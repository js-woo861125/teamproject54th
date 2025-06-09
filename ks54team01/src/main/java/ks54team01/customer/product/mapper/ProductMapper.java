package ks54team01.customer.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.product.domain.BenefitDetail;
import ks54team01.customer.product.domain.CustomerProduct;

@Mapper
public interface ProductMapper {
	
	
	
	List<BenefitDetail> getBenefitByProduct(String productsNum);
	
	
	CustomerProduct getLowerPriceAndMaxPeriod(String productsNum);
	
	List<CustomerProduct> getProductSpecList(String productsNum);
	
	List<CustomerProduct> getProductDetailByProd(String productsNum);
	
	
	//소분류별 상품목록 조회
	List<CustomerProduct> getCustomerProductList(String smallCategory);
			
	//전체상품조회
	List<CustomerProduct> getCutomeProductListAll(String productsNum);
	
}
