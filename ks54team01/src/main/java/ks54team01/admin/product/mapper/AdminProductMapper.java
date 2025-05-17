package ks54team01.admin.product.mapper;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.admin.product.domain.AdminAddProduct;

@Mapper
public interface AdminProductMapper {
	
	// 상품 등록
	int insertProduct(AdminAddProduct product);
}
