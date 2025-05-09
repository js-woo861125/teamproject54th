package ks54team01.admin.productInfo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks54team01.admin.productInfo.domain.ProductInfoBenefit;
import ks54team01.admin.productInfo.domain.ProductInfoBrand;
import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.admin.productInfo.domain.ProductInfoCategorySpec;
import ks54team01.admin.productInfo.domain.ProductInfoItem;
import ks54team01.admin.productInfo.domain.ProductInfoModel;
import ks54team01.admin.productInfo.service.AdminProductInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/productInfo")
@Slf4j
public class AdminProductInfoController {
	
	// DI 의존성 주입
	private final AdminProductInfoService adminProductInfoService;
	
	@GetMapping("/benefitList")
	public String getBenefitList(Model model) {
		
		List<ProductInfoBenefit> benefitList = adminProductInfoService.getBenefitList();
		
		model.addAttribute("title", "전체혜택");
		model.addAttribute("benefitList", benefitList);
		
		return "admin/productInfo/benefitListView";
	}	
	
	@GetMapping("/modelSpecList")
	public String modelSpecList() {
		
		return "admin/productInfo/modelSpecListView";
	}
	
	@GetMapping("/categorySpecList")
	public String categorySpecList(Model model) {
		
		List<ProductInfoCategorySpec> categorySpecList = adminProductInfoService.getCategorySpecList();
		
		model.addAttribute("title", "카테고리별/상세스펙");
		model.addAttribute("categorySpecList", categorySpecList);
		
		return "admin/productInfo/categorySpecListView";
	}	
	
	@GetMapping("/modelList")
	public String getModelList(Model model) {

		List<ProductInfoModel> modelList = adminProductInfoService.getModelList();
		
		model.addAttribute("title", "모델");
		model.addAttribute("modelList", modelList);
		
		return "admin/productInfo/modelListView";
	}
	
	@GetMapping("/itemList")
	public String getItemList(Model model) {
		
		List<ProductInfoItem> itemList = adminProductInfoService.getItemList();
		
		model.addAttribute("title", "품목");
		model.addAttribute("itemList", itemList);
		
		return "admin/productInfo/itemListView";
	}
	
	@GetMapping("/brandList")
	public String getBrandList(Model model) {
		
		List<ProductInfoBrand> brandList = adminProductInfoService.getBrandList();
		
		model.addAttribute("title", "브랜드");
		model.addAttribute("brandList", brandList);
		
		return "admin/productInfo/brandListView";
	}	
	
	@GetMapping("/categoryList")
	public String getCategoryList(Model model) {
		
		List<ProductInfoCategory> categoryList = adminProductInfoService.getCategoryList();
		
		model.addAttribute("title", "카테고리");
		model.addAttribute("categoryList", categoryList);
		
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
	
	@PostMapping("/addCategory")
	public String addCategory(ProductInfoCategory productInfoCategory) {
		
		adminProductInfoService.addCategory(productInfoCategory);
		
		return "redirect:/admin/productInfo/categoryList";
	}
	
	@GetMapping("/addCategory")
	public String addCategory(Model model) {
		
		List<ProductInfoCategory> categoryList = adminProductInfoService.getCategoryList();
		model.addAttribute("title", "카테고리 등록");
		
		return "admin/productInfo/addCategoryView";
	}
}
