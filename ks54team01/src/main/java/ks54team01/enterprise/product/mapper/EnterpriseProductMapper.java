package ks54team01.enterprise.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import ks54team01.enterprise.product.domain.EnterpriseProduct;
import ks54team01.enterprise.product.domain.EnterpriseProductBenefit;
import ks54team01.enterprise.product.domain.EnterpriseProductQuantity;

@Mapper
public interface EnterpriseProductMapper {
	List<EnterpriseProductQuantity> getQuantityList();
	
	List<EnterpriseProduct> getSellProductList();
	
	void addSellProduct(EnterpriseProduct enterpriseProduct);
	
	 int updateSellProductPrice(EnterpriseProduct product);
	
	 int countByProduct(
			    @Param("entCeoNo") String entCeoNo,
			    @Param("productsNo") String productsNo,
			    @Param("period") Integer period
			);
	 

	 EnterpriseProduct selectProductByNo(String sellProductsNo);
	 
}
