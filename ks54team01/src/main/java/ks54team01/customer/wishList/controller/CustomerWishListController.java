package ks54team01.customer.wishList.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ks54team01.customer.wishList.domain.CustomerWishList;
import ks54team01.customer.wishList.service.CustomerWishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/customer/wishList")
@RequiredArgsConstructor
@Slf4j
public class CustomerWishListController {

	private final CustomerWishListService customerWishListService;
	
	@PostMapping("/removeMyWishLists")
	@ResponseBody
	public boolean removeMyWishLists(@RequestParam(name="wishListNum") List<String> wishListNum) {
		
		boolean isRemove = customerWishListService.removeMyWishLists(wishListNum);
		
		return isRemove;
	}
	
	@PostMapping("/removeMyWishList")
	@ResponseBody
	public boolean removeMyWishList(@RequestParam(name="wishListNum") String wishListNum) {
		
		boolean isRemove = customerWishListService.removeMyWishList(wishListNum);
		
		return isRemove;
	}
	
	
	@GetMapping("/myWishList")
	public String getMyWishList(@RequestParam(name="viewValue", required = false, defaultValue = "전체") String viewValue
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
