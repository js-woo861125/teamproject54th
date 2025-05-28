package ks54team01.customer.wishList.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import ks54team01.customer.transferBoard.domain.CustomerTransferBoard;
import ks54team01.customer.wishList.domain.CustomerWishList;
import ks54team01.customer.wishList.mapper.CustomerWishListMapper;
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
	private final CustomerWishListMapper customerWishListMapper;
	
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
	
	@PostMapping("/addMyWishList")
	public String addMyWishList(@RequestParam(name="productsNum") String productsNum
							   , HttpSession session) {
		
		log.info("productsNum: {}", productsNum);
		
		String custId = (String) session.getAttribute("loginId");
		
		log.info("custId: {}", custId);
		
		CustomerWishList customerWishList = new CustomerWishList();
		customerWishList.setProductsNum(productsNum);
		customerWishList.setCustomerId(custId);
		
		int isDuplicate = customerWishListMapper.isDuplicateProductCheck(productsNum, custId);
		
		if(isDuplicate == 0) customerWishListService.addMyWishList(customerWishList);
		
		return "redirect:/customer/product/productDetailByProd?productsNum=" + productsNum;
	}
	
	/*
	 * @PostMapping("/checkDuplicate")
	 * 
	 * @ResponseBody public boolean checkDuplicate() {
	 * 
	 * boolean isDuplicate = false;
	 * 
	 * log.info("체크상품: {}", productsNum);
	 * 
	 * isDuplicate = memberService.isIdCheck(memberId);
	 * 
	 * return true; }
	 */
	
	@GetMapping("/myWishList")
	public String getMyWishList(@RequestParam(name="viewValue", required = false, defaultValue = "전체") String viewValue
								, HttpSession session
								, Model model) {
		
		String custId = (String) session.getAttribute("loginId");
		
		log.info("custId: {}", custId);
		
		List<CustomerWishList> wishListCategory = customerWishListService.getWishListCategory(custId); // 담은 카테고리 조회
		List<CustomerWishList> myWishList = customerWishListService.getMyWishList(custId, viewValue); // 관심상품 목록 조회 기본이 전체

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
