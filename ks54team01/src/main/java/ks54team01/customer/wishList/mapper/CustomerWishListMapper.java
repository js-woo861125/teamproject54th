package ks54team01.customer.wishList.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.wishList.domain.CustomerWishList;

@Mapper
public interface CustomerWishListMapper {

	// 내 관심상품 목록 조회
	List<CustomerWishList> getmyWishList();
}
