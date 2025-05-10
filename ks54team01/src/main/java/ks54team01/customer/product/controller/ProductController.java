package ks54team01.customer.product.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks54team01.customer.product.domain.CustomerProduct;
import ks54team01.customer.product.service.CustomerProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/product")
@Slf4j
public class ProductController {

	private final CustomerProductService customerProductService;
	
	@GetMapping("/productDetail")
	public String getProductDetail() {
		
		return "customer/product/productDetail";
	}
	
	
	@GetMapping("/productList")
	public String getProductList(@RequestParam(name="smallCategory") String smallCategory, Model model) {
		
		List<CustomerProduct> productList = customerProductService.getCustomerProductList(smallCategory);
		
		model.addAttribute("title", smallCategory);
		model.addAttribute("productList", productList);
		
		log.info("smallCategory : {}", smallCategory);
		log.info("customerProduct: {}",  productList);
		
		
		return "customer/product/productList";
	}
}
