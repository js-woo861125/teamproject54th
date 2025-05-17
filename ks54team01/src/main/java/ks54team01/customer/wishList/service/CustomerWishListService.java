package ks54team01.customer.wishList.service;

import java.util.List;

import ks54team01.customer.wishList.domain.CustomerWishList;

public interface CustomerWishListService {

	
	// 내 관심상품 목록 조회
	List<CustomerWishList> getmyWishList();
}
