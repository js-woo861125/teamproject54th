package ks54team01.customer.wishList.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.wishList.domain.CustomerWishList;

@Mapper
public interface CustomerWishListMapper {

	// 관심상품 다중 삭제(체크박스)
	int removeMyWishLists(List<String> orderNumList);
	
	// 관심상품 삭제
	int removeMyWishList(String wishListNum);
	
	// 아이디 별 추가한 위시리스트 목록의 카테고리 추출
	List<CustomerWishList> getWishListCategory(String custId);
	
	// 내 관심상품 목록 조회
	List<CustomerWishList> getMyWishList(String viewValue);
}
