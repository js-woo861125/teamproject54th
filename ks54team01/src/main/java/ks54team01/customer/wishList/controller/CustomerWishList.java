package ks54team01.customer.wishList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer/wishList")
public class CustomerWishList {

	@GetMapping("/wishList")
	public String getMyWishList() {
		
		return "customer/wishList/wishListView";
	}
}
