package ks54team01.customer.wishList.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String getMyWishList(@RequestParam(name="viewValue", required = false, defaultValue = "all") String viewValue
								, Model model) {
		
		String custId = "personalcustid2";
		
		List<CustomerWishList> myWishList = customerWishListService.getMyWishList(viewValue);
		List<CustomerWishList> wishListCategory = customerWishListService.getWishListCategory(custId); // 전체 카테고리 기준

		model.addAttribute("wishListCategory", wishListCategory);
		model.addAttribute("title", "내 관심상품 목록 조회");
		model.addAttribute("myWishList", myWishList);
		model.addAttribute("viewValue", viewValue);
		
		log.info("카테고리 목록: {}", wishListCategory);
		log.info("myWishList: {}", myWishList);
		log.info("viewValue: {}", viewValue);
		
		return "customer/wishList/myWishListView";
	}
}
