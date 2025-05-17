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
	public List<CustomerWishList> getmyWishList() {
		
		List<CustomerWishList> myWishList = customerWishListMapper.getmyWishList();
		return myWishList;
	}
}
