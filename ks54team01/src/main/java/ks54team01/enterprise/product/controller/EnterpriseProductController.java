package ks54team01.enterprise.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks54team01.customer.delivery.domain.CustomerDeliveryList;
import ks54team01.enterprise.product.domain.EnterpriseMarginRatio;
import ks54team01.enterprise.product.domain.EnterpriseProductQuantity;
import ks54team01.enterprise.product.service.EnterpriseMarginRatioService;
import ks54team01.enterprise.product.service.EnterpriseProductService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/enterprise")
public class EnterpriseProductController {
	
	private final EnterpriseProductService enterpriseProductService;
	private final EnterpriseMarginRatioService enterpriseMarginRatioService;
	
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
	
	@PostMapping("/product/addMarginRatio")
	public String addEnterpriseMarginRatio(EnterpriseMarginRatio enterpriseMarginRatio) {
		
		
		enterpriseMarginRatioService.addEnterpriseMarginRatio(enterpriseMarginRatio);
		
		return "redirect:/enterprise/product/marginRatio";
	}
	
	@PostMapping("/product/modifyMarginRatioUseStatus")
	public String modifyMarginRatioUseStatus(@RequestParam("marginRatioNum") String marginRatioNum
											,@RequestParam("useStatus") String useStatus) {
				
		enterpriseMarginRatioService.modifyMarginRatioUseStatus(marginRatioNum, useStatus);
		
		return "redirect:/enterprise/product/marginRatio";
	}
	
	@PostMapping("/product/modifyMarginRatio")
	public String modifymodifyMarginRatio(@RequestParam("periodList") List<Integer> periodList,
										  @RequestParam("marginRatioList") List<Double>  marginRatioList) {
		
		
		List<EnterpriseMarginRatio> list = new ArrayList<>();

	    // 예시로 "ent_ceo_2" 하드코딩
	    String entCeoNo = "ent_ceo_2";

	    for (int i = 0; i < periodList.size(); i++) {
	        EnterpriseMarginRatio emr = new EnterpriseMarginRatio();
	        emr.setPeriod(periodList.get(i));
	        emr.setMarginRatio(marginRatioList.get(i));
	        emr.setEnterpriseCeoNum(entCeoNo);
	        list.add(emr);
	    }

	    enterpriseMarginRatioService.modifyEnterpriseMarginRatio(list);
		
		return "redirect:/enterprise/product/marginRatio";
	}
	
	@GetMapping("/product/marginRatio")
	public String enterpriseMarginRatio(Model model) {
		
		List<EnterpriseMarginRatio> enterpriseMarginRatio = enterpriseMarginRatioService.getEnterpriseMarginRatio();
		
		model.addAttribute("title", "마진율 등록");
		model.addAttribute("enterpriseMarginRatio", enterpriseMarginRatio);
		
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
