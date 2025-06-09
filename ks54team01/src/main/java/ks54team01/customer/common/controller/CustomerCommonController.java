package ks54team01.customer.common.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.admin.productInfo.mapper.AdminProductInfoMapper;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CustomerCommonController {

	private final AdminProductInfoMapper adminProductInfoMapper;
	
	@GetMapping({"","/"})
	public String customerHome(Model model) {

			List<ProductInfoCategory> mdCategoryList = adminProductInfoMapper.getMdCategory();
			
			model.addAttribute("mdCategoryList", mdCategoryList);
		
		return "customer/main";
	}
}
