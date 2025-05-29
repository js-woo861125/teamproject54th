package ks54team01.customer.product.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	
	@GetMapping("/productDetailByProd")
	public String getProductDetailByProd(@RequestParam(value="productsNum") String productsNum
									   , Model model
									   , @ModelAttribute("addResult") String addResult) {
		
		List<CustomerProduct> productDetailByProd = customerProductService.getProductDetailByProd(productsNum);
		
		

		log.info("productDetailByProd:{}", productDetailByProd);
		
		model.addAttribute("productDetailByProd", productDetailByProd);
		
		model.addAttribute("addResult", addResult);
		
		
		return "customer/product/productDetailView";
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
