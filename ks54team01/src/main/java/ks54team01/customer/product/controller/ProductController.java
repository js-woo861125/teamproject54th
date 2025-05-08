package ks54team01.customer.product.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("customer/product")
@lombok.extern.slf4j.Slf4j
public class ProductController {

	@GetMapping("productDetail")
	public String getProductDetail() {
		
		return "customer/product/productDetail";
	}
	
	
	@GetMapping("productList")
	public String getProductList() {
		
//		List<Product> productList = productService.getProductList();
//		
//		model.addAttribute("title", "상품리스트");
//		model.addAttribute("productList", productList);
		
		return "customer/product/productList";
	}
}
