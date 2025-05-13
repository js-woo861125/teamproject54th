package ks54team01.admin.product.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/product")
@Slf4j
public class AdminProductController {


	
	@GetMapping("/productList")
	public String productList(Model model) {
		
		
		return "admin/product/productListView";
	}
	
	@GetMapping("/addProduct")
	public String addProduct(Model model) {
		
		
		return "admin/product/addProductView";
	}
}
