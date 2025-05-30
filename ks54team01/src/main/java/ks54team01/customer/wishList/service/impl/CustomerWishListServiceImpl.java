package ks54team01.customer.wishList.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ks54team01.customer.wishList.domain.CustomerWishList;
import ks54team01.customer.wishList.mapper.CustomerWishListMapper;
import ks54team01.customer.wishList.service.CustomerWishListService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerWishListServiceImpl implements CustomerWishListService{

	private final CustomerWishListMapper customerWishListMapper;
	
	@Override
	public void addMyWishList(CustomerWishList customerWishList) {
		
		String wishListNum =  "wishlist_" + UUID.randomUUID().toString().replace("-", "");
		customerWishList.setWishListNum(wishListNum);
		
		customerWishListMapper.addMyWishList(customerWishList);
	}
	
	@Override
	public boolean removeMyWishLists(List<String> wishListNum) {
		
		int deleted = customerWishListMapper.removeMyWishLists(wishListNum);
		
		boolean isDel = deleted > 0 ? true : false;
		
		return isDel;
	}
	
	@Override
	public boolean removeMyWishList(String wishListNum) {
		
		int deleted = customerWishListMapper.removeMyWishList(wishListNum);
		
		boolean isDel = deleted > 0 ? true : false;
		
		return isDel;
	}
	
	@Override
	public List<CustomerWishList> getWishListCategory(String customerId) {
		List<CustomerWishList> wishListCategory = customerWishListMapper.getWishListCategory(customerId);
		return wishListCategory;
	}
	
	@Override
	public List<CustomerWishList> getMyWishList(String customerId, String viewValue) {
		
		List<CustomerWishList> myWishList = customerWishListMapper.getMyWishList(customerId, viewValue);
		return myWishList;
	}
}
