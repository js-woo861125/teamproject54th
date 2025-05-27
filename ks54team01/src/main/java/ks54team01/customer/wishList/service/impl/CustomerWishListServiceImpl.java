package ks54team01.customer.wishList.service.impl;

import java.util.List;

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
	public boolean addMyWishList(String productsNum) {
		
		int added = customerWishListMapper.addMyWishList(productsNum);
		
		boolean isAdd = added > 0 ? true : false;
		
		return isAdd;
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
	public List<CustomerWishList> getWishListCategory(String custId) {
		List<CustomerWishList> wishListCategory = customerWishListMapper.getWishListCategory(custId);
		return wishListCategory;
	}
	
	@Override
	public List<CustomerWishList> getMyWishList(String viewValue) {
		
		List<CustomerWishList> myWishList = customerWishListMapper.getMyWishList(viewValue);
		return myWishList;
	}
}
