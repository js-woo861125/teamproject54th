package ks54team01.customer.wishList.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks54team01.customer.wishList.domain.CustomerWishList;
import ks54team01.customer.wishList.service.CustomerWishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/customer/wishList")
@RequiredArgsConstructor
@Slf4j
public class CustomerWishListController {

	private final CustomerWishListService customerWishListService;
	
	@GetMapping("/myWishList")
	public String getMyWishList(Model model) {
		
		List<CustomerWishList> myWishList = customerWishListService.getmyWishList();
		
		model.addAttribute("title", "내 관심상품 목록 조회");
		model.addAttribute("myWishList", myWishList);
		
		log.info("myWishList: {}", myWishList);
		
		return "customer/wishList/myWishListView";
	}
}
