package ks54team01.enterprise.prodcut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/enterprise")
public class EnterpriseProductController {
	
	@GetMapping("/product/marginRatio")
	public String enterpriseMarginRatio(Model model) {
		
		model.addAttribute("title", "마진율 등록");
		
		return "enterprise/product/enterpriseMarginRatioView";
	}
	
}
