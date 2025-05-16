package ks54team01.admin.productInfo.controller;

import java.util.List;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import ks54team01.admin.productInfo.domain.ProductInfoBenefit;
import ks54team01.admin.productInfo.domain.ProductInfoBrand;
import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.admin.productInfo.domain.ProductInfoCategorySpec;
import ks54team01.admin.productInfo.domain.ProductInfoItem;
import ks54team01.admin.productInfo.domain.ProductInfoModel;
import ks54team01.admin.productInfo.domain.ProductInfoModelSpec;
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
	
	/**
	 * 상품정보 검색
	 */
	@GetMapping("/searchBenefit")
	public String getSearchBenefit(String searchKey, String searchValue, Model model) {

		List<ProductInfoBenefit> benefitList = adminProductInfoService.getSearchBenefit(searchKey, searchValue);
		
		model.addAttribute("title", "전체혜택");
		model.addAttribute("benefitList", benefitList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		
		return "admin/productInfo/benefitListView";
		}
	
	@GetMapping("/searchItem")
	public String getSearchItem(String searchKey, String searchValue, Model model) {

		List<ProductInfoItem> itemList = adminProductInfoService.getSearchItem(searchKey, searchValue);
		
		model.addAttribute("title", "품목");
		model.addAttribute("itemList", itemList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		
		return "admin/productInfo/itemListView";
		}
	
	@GetMapping("/searchBrand")
	public String getSearchBrand(String searchKey, String searchValue, Model model) {

		List<ProductInfoBrand> brandList = adminProductInfoService.getSearchBrand(searchKey, searchValue);
		
		model.addAttribute("title", "브랜드");
		model.addAttribute("brandList", brandList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		
		return "admin/productInfo/brandListView";
		}
	
	@GetMapping("/searchCategory")
	public String getSearchCategory(String searchKey, String searchValue, Model model) {

		List<ProductInfoCategory> categoryList = adminProductInfoService.getSearchCategory(searchKey, searchValue);
		
		model.addAttribute("title", "카테고리");
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		
		return "admin/productInfo/categoryListView";
		}
	
	/**
	 * 상품정보 삭제
	 */
	@PostMapping("/removeBrand")
	@ResponseBody
	public boolean removeBrand(String brandNo) {
		log.info("삭제 할 브랜드코드: {}", brandNo);
		
		boolean isDel = adminProductInfoService.removeBrandInfoByNo(brandNo);
		
		return isDel;
	}
	
	/**
	 * 상품정보 중복체크
	 */
	@PostMapping("/benefitNameCheck")
	@ResponseBody
	public boolean benefitNameCheck(String benefitName) {
		boolean isDuplicate =  false;
		
		log.info("체크혜택명 : {}", benefitName);
		
		isDuplicate = adminProductInfoService.isBenefitNameCheck(benefitName);
		
		return isDuplicate;
	}
	
	@PostMapping("/itemNameCheck")
	@ResponseBody
	public boolean itemNameCheck(String itemName, String categoryNo) {
		boolean isDuplicate =  false;
		
		log.info("체크품목명 : {}", itemName);
		log.info("체크카테고리코드 : {}", categoryNo);
		
		isDuplicate = adminProductInfoService.isItemNameCheck(itemName, categoryNo);
		
		return isDuplicate;
	}
	
	@PostMapping("/brandNameCheck")
	@ResponseBody
	public boolean brandNameCheck(String brandName) {
		boolean isDuplicate =  false;
		
		log.info("체크브랜드명 : {}", brandName);
		
		isDuplicate = adminProductInfoService.isBrandNameCheck(brandName);
		
		return isDuplicate;
	}
	
	
	/**
	 * 상품정보 수정
	 */
	@PostMapping("/modifyCategorySpec")
	public String modifyCategorySpec(ProductInfoCategorySpec productInfoCategorySpec, RedirectAttributes reAttr) {
		
		adminProductInfoService.modifyCategorySpec(productInfoCategorySpec);
		
		reAttr.addAttribute("specNo", productInfoCategorySpec.getSpecNo());
		
		return "redirect:/admin/productInfo/categorySpecList";
	}
	
	@GetMapping("/modifyCategorySpec")
	public String modifyCategorySpec(String specNo, Model model) {		
		
		ProductInfoCategorySpec specInfo = adminProductInfoService.getCategorySpecInfoByNo(specNo);
		
		model.addAttribute("title", "스펙 수정");
		
		return "admin/productInfo/modifyCategorySpecView";
	}
	
	@PostMapping("/modifyBenefit")
	public String modifyBenefit(ProductInfoBenefit productInfoBenefit, RedirectAttributes reAttr) {
		
		adminProductInfoService.modifyBenefit(productInfoBenefit);
		
		reAttr.addAttribute("benefitNo", productInfoBenefit.getBenefitNo());
		
		return "redirect:/admin/productInfo/benefitList";
	}
	
	@GetMapping("/modifyBenefit")
	public String modifyBenefit(String benefitNo, Model model) {		
		
		ProductInfoBenefit benefitInfo = adminProductInfoService.getBenefitInfoByNo(benefitNo);
		
		model.addAttribute("title", "혜택 수정");
		
		return "admin/productInfo/modifyBenefitView";
	}
	
	@PostMapping("/modifyModel")
	public String modifyModel(ProductInfoModel productInfoModel, RedirectAttributes reAttr) {
		
		adminProductInfoService.modifyModel(productInfoModel);
		
		reAttr.addAttribute("modelNo", productInfoModel.getModelNo());
		
		return "redirect:/admin/productInfo/modelList";
	}
	
	@GetMapping("/modifyModel")
	public String modifyModel(String modelNo, Model model) {		
		
		ProductInfoModel modelInfo = adminProductInfoService.getModelInfoByNo(modelNo);
		
		model.addAttribute("title", "모델 수정");
		
		return "admin/productInfo/modifyModelView";
	}
	
	@PostMapping("/modifyItem")
	public String modifyItem(ProductInfoItem productInfoItem, RedirectAttributes reAttr) {
		
		adminProductInfoService.modifyItem(productInfoItem);
		
		reAttr.addAttribute("itemNo", productInfoItem.getItemNo());
		
		return "redirect:/admin/productInfo/itemList";
	}
	
	@GetMapping("/modifyItem")
	public String modifyItem(String itemNo, Model model) {		
		
		ProductInfoItem itemInfo = adminProductInfoService.getItemInfoByNo(itemNo);
		List<ProductInfoCategory> categoryList = adminProductInfoService.getCategoryList();
		
		model.addAttribute("title", "품목 수정");
		model.addAttribute("itemInfo", itemInfo);
		model.addAttribute("categoryList", categoryList);
		
		return "admin/productInfo/modifyItemView";
	}
	
	@PostMapping("/modifyBrand")
	public String modifyBrand(ProductInfoBrand productInfoBrand, RedirectAttributes reAttr) {
		
		adminProductInfoService.modifyBrand(productInfoBrand);
		
		reAttr.addAttribute("brandNo", productInfoBrand.getBrandNo());
		
		return "redirect:/admin/productInfo/brandList";
	}
	
	@GetMapping("/modifyBrand")
	public String modifyBrand(String brandNo, Model model) {		
		
		ProductInfoBrand brandInfo = adminProductInfoService.getBrandInfoByNo(brandNo);
		
		model.addAttribute("title", "브랜드 수정");
		model.addAttribute("brandInfo", brandInfo);
		
		return "admin/productInfo/modifyBrandView";
	}
	
	@PostMapping("/modifyCategory")
	public String modifyCategory(ProductInfoCategory productInfoCategory, RedirectAttributes reAttr) {
		
		adminProductInfoService.modifyCategory(productInfoCategory);
		
		reAttr.addAttribute("categoryNo", productInfoCategory.getCategoryNo());
		
		return "redirect:/admin/productInfo/categoryList";
	}
	
	@GetMapping("/modifyCategory")
	public String modifyCategory(String categoryNo, Model model) {		
		
		ProductInfoCategory categoryInfo = adminProductInfoService.getCategoryInfoByNo(categoryNo);
		
		model.addAttribute("title", "카테고리 수정");
		model.addAttribute("categoryInfo", categoryInfo);
		
		return "admin/productInfo/modifyCategoryView";
	}
	
	/**
	 * 상품정보 목록
	 */
	@GetMapping("/benefitList")
	public String getBenefitList(Model model) {
		
		List<ProductInfoBenefit> benefitList = adminProductInfoService.getBenefitList();
		
		model.addAttribute("title", "전체혜택");
		model.addAttribute("benefitList", benefitList);
		
		return "admin/productInfo/benefitListView";
	}	
	
	@GetMapping("/modelSpecList")
	public String modelSpecList(Model model) {
		
		List<ProductInfoModelSpec> modelSpecList = adminProductInfoService.getModelSpecList();
		
		model.addAttribute("title", "모델별/상세스펙");
		model.addAttribute("modelSpecList", modelSpecList);
		
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
	
	/**
	 * 상품정보 등록
	 */
	@PostMapping("/addBenefit")
	public String addBenefit(ProductInfoBenefit productInfoBenefit) {
		
		adminProductInfoService.addBenefit(productInfoBenefit);
		
		return "redirect:/admin/productInfo/benefitList";
	}

	@GetMapping("/addBenefit")
	public String addBenefit(Model model) {
		
		model.addAttribute("title", "혜택 등록");
		
		return "admin/productInfo/addBenefitView";
	}
	
	@GetMapping("/addModelSpec")
	public String addModelSpec() {
		
		return "admin/productInfo/addModelSpecView";
	}
	
	@PostMapping("/addCategorySpec")
	public String addCategorySpec(ProductInfoCategorySpec productInfoCategorySpec) {
		
		adminProductInfoService.addCategorySpec(productInfoCategorySpec);
		
		return "redirect:/admin/productInfo/categorySpecList";
	}
	
	@GetMapping("/addCategorySpec")
	public String addCategorySpec(Model model) {
		
		List<ProductInfoCategory> categoryList = adminProductInfoService.getCategoryList();
		
		model.addAttribute("title", "카테고리별/상세스펙 등록");
		model.addAttribute("categoryList", categoryList);
		
		return "admin/productInfo/addCategorySpecView";
	}
	
	@PostMapping("/addModel")
	public String addModel(ProductInfoModel productInfoModel) {
		
		adminProductInfoService.addModel(productInfoModel);
		
		return "redirect:/admin/productInfo/modelList";
	}
	
	@GetMapping("/addModel")
	public String addModel(Model model) {
		
		List<ProductInfoBrand> brandList = adminProductInfoService.getBrandList();
		List<ProductInfoItem> itemList = adminProductInfoService.getItemList();
		List<ProductInfoCategory> categoryList = adminProductInfoService.getCategoryList();
		
		model.addAttribute("title", "모델 등록");
		model.addAttribute("brandList", brandList);
		model.addAttribute("itemList", itemList);
		model.addAttribute("categoryList", categoryList);
		
		return "admin/productInfo/addModelView";
	}
	
	@PostMapping("/addItem")
	public String addItem(ProductInfoItem productInfoItem) {
		
		adminProductInfoService.addItem(productInfoItem);
		
		return "redirect:/admin/productInfo/itemList";
	}
	
	@GetMapping("/addItem")
	public String addItem(Model model) {
		
		List<ProductInfoCategory> categoryList = adminProductInfoService.getCategoryList();
		
		model.addAttribute("title", "품목 등록");
		model.addAttribute("categoryList", categoryList);
		
		return "admin/productInfo/addItemView";
	}
	
	@PostMapping("/addBrand")
	public String addBrand(ProductInfoBrand productInfoBrand) {
		
		adminProductInfoService.addBrand(productInfoBrand);
		
		return "redirect:/admin/productInfo/brandList";
	}
	
	@GetMapping("/addBrand")
	public String addBrand(Model model) {
		
		model.addAttribute("title", "브랜드 등록");
		
		return "admin/productInfo/addBrandView";
	}
	
	@PostMapping("/addCategory")
	public String addCategory(ProductInfoCategory productInfoCategory) {
		
		adminProductInfoService.addCategory(productInfoCategory);
		
		return "redirect:/admin/productInfo/categoryList";
	}
	
	@GetMapping("/addCategory")
	public String addCategory(Model model) {
		
		model.addAttribute("title", "카테고리 등록");
		
		return "admin/productInfo/addCategoryView";
	}
}
