package ks54team01.enterprise.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import ks54team01.enterprise.product.domain.EnterpriseProduct;
import ks54team01.enterprise.product.domain.EnterpriseProductQuantity;

@Mapper
public interface EnterpriseProductMapper {
	List<EnterpriseProductQuantity> getQuantityList();
	
	List<EnterpriseProduct> getSellProductList();
	
	void addSellProduct(EnterpriseProduct enterpriseProduct);
	
	 int updateSellProduct(EnterpriseProduct product);
	
	 int countByProduct(
			    @Param("entCeoNo") String entCeoNo,
			    @Param("productsNo") String productsNo,
			    @Param("period") Integer period
			);
	 

	 EnterpriseProduct getProductByNo(String sellProductsNo);

	 List<EnterpriseProduct> searchSellProductList(
			    @Param("searchKey") String searchKey,
			    @Param("searchValue") String searchValue,
			    @Param("categoryNo") String categoryNo,
			    @Param("status") String status
			);
	 void setSaleStoppage(@Param("sellProductNo")String sellProductNo);
	    
	 void unsetSaleStoppage(@Param("sellProductNo") String sellProductNo);

	 int updateQuantity(String productsNo, int quantity);
	 
}
