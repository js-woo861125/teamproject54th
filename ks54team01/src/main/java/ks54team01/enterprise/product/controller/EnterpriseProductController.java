package ks54team01.enterprise.product.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks54team01.enterprise.product.domain.EnterpriseProductQuantity;
import ks54team01.enterprise.product.service.EnterpriseProductService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/enterprise")
public class EnterpriseProductController {
	
	private final EnterpriseProductService enterpriseProductService;
	
	@GetMapping("/product/sellProductList")
	public String sellProductList(Model model) {
		
		model.addAttribute("title", "판매 상품목록");
		
		return "enterprise/product/enterpriseProductListView";
	}
	
	@GetMapping("/product/productList")
	public String productList(Model model) {
		
		model.addAttribute("title", "플랫폼 상품목록");
		
		return "enterprise/product/platformProductListView";
	}
	
	@GetMapping("/product/marginRatio")
	public String enterpriseMarginRatio(Model model) {
		
		model.addAttribute("title", "마진율 등록");
		
		return "enterprise/product/enterpriseMarginRatioView";
	}
	
	@GetMapping("/product/quantityList")
	public String quantityList(Model model) {
		
		List<EnterpriseProductQuantity> quantityList = enterpriseProductService.getQuantityList();
		
		model.addAttribute("title", "재고관리");
		model.addAttribute("quantityList", quantityList);
		
		return "enterprise/product/enterpriseQuantityView";
	}
}
