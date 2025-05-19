package ks54team01.customer.wishList.service;

import java.util.List;

import ks54team01.customer.wishList.domain.CustomerWishList;

public interface CustomerWishListService {

	// 관싱상품 삭제
	boolean removeMyWishLists(List<String> wishListNum);
	
	// 관싱상품 삭제
	boolean removeMyWishList(String wishListNum);
	
	// 아이디 별 추가한 위시리스트 목록의 카테고리 추출
	List<CustomerWishList> getWishListCategory(String custId);
	
	// 내 관심상품 목록 조회
	List<CustomerWishList> getMyWishList(String viewValue);
}
