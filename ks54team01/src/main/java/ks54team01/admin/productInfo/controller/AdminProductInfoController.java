package ks54team01.admin.productInfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/productInfo")
public class AdminProductInfoController {
	
	@GetMapping("/benefitList")
	public String benefitList() {
		
		return "admin/productInfo/benefitListView";
	}	
	
	@GetMapping("/modelSpecList")
	public String modelSpecList() {
		
		return "admin/productInfo/modelSpecListView";
	}
	
	@GetMapping("/categorySpecList")
	public String categorySpecList() {
		
		return "admin/productInfo/categorySpecListView";
	}	
	
	@GetMapping("/modelList")
	public String modelList() {
		
		return "admin/productInfo/modelListView";
	}
	
	@GetMapping("/itemList")
	public String itemList() {
		
		return "admin/productInfo/itemListView";
	}
	
	@GetMapping("/brandList")
	public String brandList() {
		
		return "admin/productInfo/brandListView";
	}	
	
	@GetMapping("/categoryList")
	public String categoryList() {
		
		return "admin/productInfo/categoryListView";
	}	

	@GetMapping("/addBenefit")
	public String addBenefit() {
		
		return "admin/productInfo/addBenefitView";
	}
	
	@GetMapping("/addModelSpec")
	public String addModelSpec() {
		
		return "admin/productInfo/addModelSpecView";
	}
	
	@GetMapping("/addCategorySpec")
	public String addCategorySpec() {
		
		return "admin/productInfo/addCategorySpecView";
	}
	
	@GetMapping("/addModel")
	public String addModel() {
		
		return "admin/productInfo/addModelView";
	}
	
	@GetMapping("/addItem")
	public String addItem() {
		
		return "admin/productInfo/addItemView";
	}
	
	@GetMapping("/addBrand")
	public String addBrand() {
		
		return "admin/productInfo/addBrandView";
	}
	
	@GetMapping("/addCategory")
	public String addCategory() {
		
		return "admin/productInfo/addCategoryView";
	}
}
