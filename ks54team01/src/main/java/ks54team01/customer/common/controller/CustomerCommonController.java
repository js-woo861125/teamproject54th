package ks54team01.customer.common.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ks54team01.customer.product.domain.CustomerProduct;
import ks54team01.customer.product.service.CustomerProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@Slf4j

public class CustomerCommonController {



	private final CustomerProductService customerProductService;
	
	
	@GetMapping({"","/"})
	public String customerHome(
								String productsNum, Model model) {
	
		
		List<CustomerProduct> productListAll = customerProductService.getCutomeProductListAll(productsNum);
		
		model.addAttribute("productListAll", productListAll);
		
		log.info("customerProductAll: {}", productListAll);
		

		
		return "customer/main";
	}
}
