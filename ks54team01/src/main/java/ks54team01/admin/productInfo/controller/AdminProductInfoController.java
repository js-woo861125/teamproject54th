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
	@GetMapping("/searchModelSpec")
	public String getSearchModelSpec(String searchKey, String searchValue, Model model) {

		List<ProductInfoModelSpec> modelSpecList = adminProductInfoService.getSearchModelSpec(searchKey, searchValue);
		
		model.addAttribute("title", "모델별/상세스펙 검색결과");
		model.addAttribute("modelSpecList", modelSpecList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		
		return "admin/productInfo/modelSpecListView";
		}
		
	
	@GetMapping("/searchCategorySpec")
	public String getSearchCategorySpec(String searchKey, String searchValue, Model model) {

		List<ProductInfoCategorySpec> categorySpecList = adminProductInfoService.getSearchCategorySpec(searchKey, searchValue);
		
		model.addAttribute("title", "카테고리별/상세스펙 검색결과");
		model.addAttribute("categorySpecList", categorySpecList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		
		return "admin/productInfo/categorySpecListView";
		}
	
	@GetMapping("/searchBenefit")
	public String getSearchBenefit(String searchKey, String searchValue, Model model) {

		List<ProductInfoBenefit> benefitList = adminProductInfoService.getSearchBenefit(searchKey, searchValue);
		
		model.addAttribute("title", "혜택 검색결과");
		model.addAttribute("benefitList", benefitList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		
		return "admin/productInfo/benefitListView";
		}
	
	@GetMapping("/searchModel")
	public String getSearchModel(String searchKey, String searchValue, Model model) {

		List<ProductInfoModel> modelList = adminProductInfoService.getSearchModel(searchKey, searchValue);
		
		model.addAttribute("title", "모델 검색결과");
		model.addAttribute("modelList", modelList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		
		return "admin/productInfo/modelListView";
		}
	
	@GetMapping("/searchItem")
	public String getSearchItem(String searchKey, String searchValue, Model model) {

		List<ProductInfoItem> itemList = adminProductInfoService.getSearchItem(searchKey, searchValue);
		
		model.addAttribute("title", "품목 검색결과");
		model.addAttribute("itemList", itemList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		
		return "admin/productInfo/itemListView";
		}
	
	@GetMapping("/searchBrand")
	public String getSearchBrand(String searchKey, String searchValue, Model model) {

		List<ProductInfoBrand> brandList = adminProductInfoService.getSearchBrand(searchKey, searchValue);
		
		model.addAttribute("title", "브랜드 검색결과");
		model.addAttribute("brandList", brandList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		
		return "admin/productInfo/brandListView";
		}
	
	@GetMapping("/searchCategory")
	public String getSearchCategory(String searchKey, String searchValue, Model model) {

		List<ProductInfoCategory> categoryList = adminProductInfoService.getSearchCategory(searchKey, searchValue);
		
		model.addAttribute("title", "카테고리 검색결과");
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		
		return "admin/productInfo/categoryListView";
		}
	
	/**
	 * 상품정보 삭제
	 */
	@PostMapping("/removeItem")
	@ResponseBody
	public boolean removeItem(String itemNo) {
		log.info("삭제 할 품목코드 : {}", itemNo);
		
		boolean isDel = adminProductInfoService.removeItemInfoByNo(itemNo);
		
		return isDel;
	}
	
	@PostMapping("/removeBrand")
	@ResponseBody
	public boolean removeBrand(String brandNo) {
		log.info("삭제 할 브랜드코드: {}", brandNo);
		
		boolean isDel = adminProductInfoService.removeBrandInfoByNo(brandNo);
		
		return isDel;
	}
	
	@PostMapping("/removeCategory")
	@ResponseBody
	public boolean removeCategory(String categoryNo) {
		log.info("삭제 할 카테고리코드: {}", categoryNo);
		
		boolean isDel = adminProductInfoService.removeCategoryInfoByNo(categoryNo);
		
		return isDel;
	}
	
	/**
	 * 상품정보 중복체크
	 */
	@PostMapping("/specContentCheck")
	@ResponseBody
	public boolean specContentCheck(String modelSpecName, String modelNo, String specNo) {
		boolean isDuplicate =  false;
		
		log.info("체크상세스펙내용 : {}", modelSpecName);
		log.info("체크모델코드 : {}", modelNo);
		log.info("체크스펙코드 : {}", specNo);
		
		isDuplicate = adminProductInfoService.isSpecContentCheck(modelSpecName, modelNo, specNo);
		
		return isDuplicate;
	}
	
	@PostMapping("/specNameCheck")
	@ResponseBody
	public boolean specNameCheck(String specName, String categoryNo) {
		boolean isDuplicate =  false;
		
		log.info("체크스펙명 : {}", specName);
		log.info("체크카테고리코드 : {}", categoryNo);
		
		isDuplicate = adminProductInfoService.isSpecNameCheck(specName, categoryNo);
		
		return isDuplicate;
	}
	
	@PostMapping("/benefitNameCheck")
	@ResponseBody
	public boolean benefitNameCheck(String benefitName) {
		boolean isDuplicate =  false;
		
		log.info("체크혜택명 : {}", benefitName);
		
		isDuplicate = adminProductInfoService.isBenefitNameCheck(benefitName);
		
		return isDuplicate;
	}
	
	@PostMapping("/modelNameCheck")
	@ResponseBody
	public boolean modelNameCheck(String categoryNo, String brandNo, String itemNo, String modelName) {
		boolean isDuplicate =  false;
		
		log.info("체크모델명 : {}", modelName);
		log.info("체크품목코드 : {}", itemNo);
		log.info("체크브랜드코드 : {}", brandNo);
		log.info("체크카테고리코드 : {}", categoryNo);
		
		isDuplicate = adminProductInfoService.isModelNameCheck(categoryNo, brandNo, itemNo, modelName);
		
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
	
	@PostMapping("/categoryCheck")
	@ResponseBody
	public boolean categoryCheck(String lgCategory, String mdCategory, String smCategory) {
		
		boolean isDuplicate =  false;
		
		log.info("체크대분류 : {}", lgCategory);
		log.info("체크중분류 : {}", mdCategory);
		log.info("체크소분류 : {}", smCategory);
		
		isDuplicate = adminProductInfoService.isCategoryCheck(lgCategory, mdCategory, smCategory);
		
		return isDuplicate;
	}
	
	
	/**
	 * 상품정보 수정
	 */
	@PostMapping("/modifyModelSpec")
	public String modifyModelSpec(ProductInfoModelSpec productInfoModelSpec, RedirectAttributes reAttr) {
		
		adminProductInfoService.modifyModelSpec(productInfoModelSpec);
		
		reAttr.addAttribute("modelSpecNo", productInfoModelSpec.getModelSpecNo());
		
		return "redirect:/admin/productInfo/modelSpecList";
	}
	
	@GetMapping("/modifyModelSpec")
	public String modifyModelSpec(String modelSpecNo, Model model) {		
		
		ProductInfoModelSpec modelSpecInfo = adminProductInfoService.getModelSpecInfoByNo(modelSpecNo);
		List<ProductInfoModel> modelList = adminProductInfoService.getModelList();
		List<ProductInfoCategorySpec> categorySpecList = adminProductInfoService.getCategorySpecList();
		
		model.addAttribute("title", "모델별/상세스펙 수정");
		model.addAttribute("modelSpecInfo", modelSpecInfo);
		model.addAttribute("modelList", modelList);
		model.addAttribute("categorySpecList", categorySpecList);
		model.addAttribute("activeMenu", "productInfo");
		
		return "admin/productInfo/modifyModelSpecView";
	}
	
	@PostMapping("/modifyCategorySpec")
	public String modifyCategorySpec(ProductInfoCategorySpec productInfoCategorySpec, RedirectAttributes reAttr) {
		
		adminProductInfoService.modifyCategorySpec(productInfoCategorySpec);
		
		reAttr.addAttribute("specNo", productInfoCategorySpec.getSpecNo());
		
		return "redirect:/admin/productInfo/categorySpecList";
	}
	
	@GetMapping("/modifyCategorySpec")
	public String modifyCategorySpec(String specNo, Model model) {		
		
		ProductInfoCategorySpec specInfo = adminProductInfoService.getCategorySpecInfoByNo(specNo);
		List<ProductInfoCategory> categoryList = adminProductInfoService.getCategoryList();
		
		model.addAttribute("title", "카테고리별/상세스펙 수정");
		model.addAttribute("specInfo", specInfo);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("activeMenu", "productInfo");
		
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
		model.addAttribute("benefitInfo", benefitInfo);
		model.addAttribute("activeMenu", "productInfo");
		
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
		List<ProductInfoCategory> categoryList = adminProductInfoService.getCategoryList();
		List<ProductInfoBrand> brandList = adminProductInfoService.getBrandList();
		List<ProductInfoItem> itemList = adminProductInfoService.getItemList();
		
		model.addAttribute("title", "모델 수정");
		model.addAttribute("modelInfo", modelInfo);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("brandList", brandList);
		model.addAttribute("itemList", itemList);
		model.addAttribute("activeMenu", "productInfo");
		
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
		model.addAttribute("activeMenu", "productInfo");
		
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
		model.addAttribute("activeMenu", "productInfo");
		
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
	@GetMapping("/modelSpecList")
	public String modelSpecList(Model model) {
		
		List<ProductInfoModelSpec> modelSpecList = adminProductInfoService.getModelSpecList();
		
		model.addAttribute("title", "모델별/상세스펙 목록");
		model.addAttribute("modelSpecList", modelSpecList);
		model.addAttribute("activeMenu", "productInfo");
		model.addAttribute("activeSubMenu", "productInfoList");
		
		return "admin/productInfo/modelSpecListView";
	}
	
	@GetMapping("/categorySpecList")
	public String categorySpecList(Model model) {
		
		List<ProductInfoCategorySpec> categorySpecList = adminProductInfoService.getCategorySpecList();
		
		model.addAttribute("title", "카테고리별/상세스펙 목록");
		model.addAttribute("categorySpecList", categorySpecList);
		model.addAttribute("activeMenu", "productInfo");
		model.addAttribute("activeSubMenu", "productInfoList");
		
		return "admin/productInfo/categorySpecListView";
	}	
	
	@GetMapping("/benefitList")
	public String getBenefitList(Model model) {
		
		List<ProductInfoBenefit> benefitList = adminProductInfoService.getBenefitList();
		
		model.addAttribute("title", "혜택 목록");
		model.addAttribute("benefitList", benefitList);
		model.addAttribute("activeMenu", "productInfo");
		model.addAttribute("activeSubMenu", "productInfoList");
		
		return "admin/productInfo/benefitListView";
	}
	
	@GetMapping("/modelList")
	public String getModelList(Model model) {

		List<ProductInfoModel> modelList = adminProductInfoService.getModelList();
		
		model.addAttribute("title", "모델 목록");
		model.addAttribute("modelList", modelList);
		model.addAttribute("activeMenu", "productInfo");
		model.addAttribute("activeSubMenu", "productInfoList");
		
		return "admin/productInfo/modelListView";
	}
	
	@GetMapping("/itemList")
	public String getItemList(Model model) {
		
		List<ProductInfoItem> itemList = adminProductInfoService.getItemList();
		
		model.addAttribute("title", "품목 목록");
		model.addAttribute("itemList", itemList);
		model.addAttribute("activeMenu", "productInfo");
		model.addAttribute("activeSubMenu", "productInfoList");
		
		return "admin/productInfo/itemListView";
	}
	
	@GetMapping("/brandList")
	public String getBrandList(Model model) {
		
		List<ProductInfoBrand> brandList = adminProductInfoService.getBrandList();
		
		model.addAttribute("title", "브랜드 목록");
		model.addAttribute("brandList", brandList);
		model.addAttribute("activeMenu", "productInfo");
		model.addAttribute("activeSubMenu", "productInfoList");
		
		return "admin/productInfo/brandListView";
	}	
	
	@GetMapping("/categoryList")
	public String getCategoryList(Model model) {
		
		List<ProductInfoCategory> categoryList = adminProductInfoService.getCategoryList();
		
		model.addAttribute("title", "카테고리 목록");
		model.addAttribute("categoryList", categoryList);
		
		return "admin/productInfo/categoryListView";
	}	
	
	/**
	 * 모델별/상세스펙 등록할 때, 모델코드로 스펙 목록 조회
	 */
	@GetMapping("specListByModel")
	@ResponseBody
	public List<ProductInfoCategorySpec> getSpecListByModel(@RequestParam String modelNo) {
		// 1. 모델 번호로 카테고리 번호 조회
		String categoryNo = adminProductInfoService.getCategoryNoByModelNo(modelNo);
		
		// 2. 카테고리 번호로 스펙 목록 조회
		return adminProductInfoService.getSpecListByCategoryNo(categoryNo);
	}

	/**
	 * 모델 등록할 때, 카테고리코드로 품목 목록 조회
	 */
	@GetMapping("itemListByCategory")
	@ResponseBody
	public List<ProductInfoItem> getItemListByCategoryNo(@RequestParam String categoryNo) {
		
		return adminProductInfoService.getItemListByCategoryNo(categoryNo);
	}
	
	/**
	 * 상품정보 등록
	 */
	@PostMapping("/addModelSpec")
	public String addModelSpec(ProductInfoModelSpec productInfoModelSpec) {
		
		adminProductInfoService.addModelSpec(productInfoModelSpec);
		
		return "redirect:/admin/productInfo/modelSpecList";
	}
	
	@GetMapping("/addModelSpec")
	public String addModelSpec(Model model) {
		
		List<ProductInfoModel> modelList = adminProductInfoService.getModelList();
		List<ProductInfoCategorySpec> categorySpecList = adminProductInfoService.getCategorySpecList();
		
		model.addAttribute("title", "모델별/상세스펙 등록");
		model.addAttribute("modelList", modelList);
		model.addAttribute("categorySpecList", categorySpecList);
		model.addAttribute("activeMenu", "productInfo");
		model.addAttribute("activeSubMenu", "addProductInfo");
		
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
		model.addAttribute("activeMenu", "productInfo");
		model.addAttribute("activeSubMenu", "addProductInfo");
		
		return "admin/productInfo/addCategorySpecView";
	}
	
	@PostMapping("/addBenefit")
	public String addBenefit(ProductInfoBenefit productInfoBenefit) {
		
		adminProductInfoService.addBenefit(productInfoBenefit);
		
		return "redirect:/admin/productInfo/benefitList";
	}
	
	@GetMapping("/addBenefit")
	public String addBenefit(Model model) {
		
		model.addAttribute("title", "혜택 등록");
		model.addAttribute("activeMenu", "productInfo");
		model.addAttribute("activeSubMenu", "addProductInfo");
		
		return "admin/productInfo/addBenefitView";
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
		model.addAttribute("activeMenu", "productInfo");
		model.addAttribute("activeSubMenu", "addProductInfo");
		
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
		model.addAttribute("activeMenu", "productInfo");
		model.addAttribute("activeSubMenu", "addProductInfo");
		
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
		model.addAttribute("activeMenu", "productInfo");
		model.addAttribute("activeSubMenu", "addProductInfo");
		
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
