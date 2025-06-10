package ks54team01.enterprise.product.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ks54team01.admin.product.domain.AdminProduct;
import ks54team01.admin.product.service.AdminProductService;
import ks54team01.admin.productInfo.domain.ProductInfoBenefit;
import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.admin.productInfo.service.AdminProductInfoService;
import ks54team01.enterprise.product.domain.EnterpriseMarginRatio;
import ks54team01.enterprise.product.domain.EnterprisePenaltyCalculate;
import ks54team01.enterprise.product.domain.EnterpriseProduct;
import ks54team01.enterprise.product.domain.EnterpriseProductBenefit;
import ks54team01.enterprise.product.domain.EnterpriseProductQuantity;
import ks54team01.enterprise.product.domain.EnterpriseSellProductAllRequest;
import ks54team01.enterprise.product.domain.EnterpriseSellProductRequest;
import ks54team01.enterprise.product.mapper.EnterpriseMarginRatioMapper;
import ks54team01.enterprise.product.service.EnterpriseMarginRatioService;
import ks54team01.enterprise.product.service.EnterpriseProductService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/enterprise/product")
public class EnterpriseProductController {
	
	
	@Value("${file.path:/}")
	private String fileRealPath;
	
	private final EnterpriseProductService enterpriseProductService;
	private final EnterpriseMarginRatioService enterpriseMarginRatioService;
	private final EnterpriseMarginRatioMapper enterpriseMarginRatioMapper;
	private final AdminProductService adminProductService;
	private final AdminProductInfoService adminProductInfoService;
	
	
	
	/*
	 * 입점업체 등록 상품 리스트
	 */
	@GetMapping("/sellProductList")
	public String getsellProductList(
	        @RequestParam(required = false) String searchKey,
	        @RequestParam( required = false) String searchValue,
	        @RequestParam(required = false) String categoryNo,
	        @RequestParam(required = false) String status,
	        Model model) {

	    List<EnterpriseProduct> sellProductList = enterpriseProductService.searchSellProductList(searchKey, searchValue, categoryNo, status);
	    List<ProductInfoCategory> categoryList = adminProductService.loadCategoryList();
	    
	  
	    model.addAttribute("title", "판매 상품목록");
	    model.addAttribute("sellProductList", sellProductList);

	    model.addAttribute("searchKey", searchKey);
	    model.addAttribute("searchValue", searchValue);
	    model.addAttribute("categoryList", categoryList);
	    model.addAttribute("categoryNo", categoryNo);
	    model.addAttribute("status", status);
	    return "enterprise/product/enterpriseProductListView";
	}
	/*
	 * 플랫폼 등록 상품 리스트
	 */
	@GetMapping("/productList")
	public String getproductList(
	        @RequestParam(required = false) String searchKey,
	        @RequestParam(required = false) String searchValue,
	        @RequestParam(required = false) String categoryNo,
	        @RequestParam(required = false) String status,
	        Model model) {

	    List<AdminProduct> getProductList = enterpriseProductService.getProductList(searchKey, searchValue, categoryNo, status);
	    List<ProductInfoCategory> categoryList = adminProductService.loadCategoryList();

	    // '판매중단' 제외 필터
	    List<AdminProduct> filteredList = getProductList.stream()
	        .filter(p -> !"판매중단".equals(p.getProductStatus()))
	        .collect(Collectors.toList());

	    model.addAttribute("title", "플랫폼 상품목록");
	    model.addAttribute("getProductList", filteredList);
	    model.addAttribute("searchKey", searchKey);
	    model.addAttribute("searchValue", searchValue);
	    model.addAttribute("categoryList", categoryList);
	    model.addAttribute("categoryNo", categoryNo);
	    model.addAttribute("status", status);

	    return "enterprise/product/platformProductListView";
	}
	
	@PostMapping("/addMarginRatio")
	public String addEnterpriseMarginRatio(EnterpriseMarginRatio enterpriseMarginRatio
										 , RedirectAttributes redirectAttributes) {
		
		int isAdd = enterpriseMarginRatioMapper.existMarginRatioByPeriod(enterpriseMarginRatio);
		
		if (isAdd == 1) {
		    redirectAttributes.addFlashAttribute("addResult", "duplicate");
		} else {
		    enterpriseMarginRatioService.addEnterpriseMarginRatio(enterpriseMarginRatio);
		    redirectAttributes.addFlashAttribute("addResult", "success");
		}
		/*
		 * 
		 * redirectAttributes.addFlashAttribute("addResult", isAdd == 1 ? "duplicate" :
		 * "success");
		 * enterpriseMarginRatioService.addEnterpriseMarginRatio(enterpriseMarginRatio);
		 */
		
		return "redirect:/enterprise/product/marginRatio";
	}
	
	@PostMapping("/modifyMarginRatioUseStatus")
	public String modifyMarginRatioUseStatus(@RequestParam("marginRatioNum") String marginRatioNum
											,@RequestParam("useStatus") String useStatus) {
				
		enterpriseMarginRatioService.modifyMarginRatioUseStatus(marginRatioNum, useStatus);
		
		return "redirect:/enterprise/product/marginRatio";
	}
	
	@PostMapping("/modifyMarginRatio")
	public String modifymodifyMarginRatio(@RequestParam("periodList") List<Integer> periodList,
										  @RequestParam("marginRatioList") List<Double>  marginRatioList) {
		
		
		List<EnterpriseMarginRatio> list = new ArrayList<>();

	    // 예시로 "ent_ceo_1" 하드코딩
	    String entCeoNo = "ent_ceo_1";

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
	
	@GetMapping("/marginRatio")
	public String enterpriseMarginRatio(@ModelAttribute("addResult") String addResult, Model model) {
		
		List<EnterpriseMarginRatio> enterpriseMarginRatio = enterpriseMarginRatioService.getEnterpriseMarginRatio();
		
		model.addAttribute("title", "마진율 등록");
		model.addAttribute("enterpriseMarginRatio", enterpriseMarginRatio);
		model.addAttribute("addResult", addResult);
		
		return "enterprise/product/enterpriseMarginRatioView";
	}
	/*
	 * 입점업체 재고 조회
	 */
	@GetMapping("/quantityList")
	public String quantityList(@RequestParam(required = false) String searchKey,
							   @RequestParam(required = false) String searchValue,
							   @RequestParam(required = false) String categoryNo,
							   @RequestParam(required = false) String status,
							   @RequestParam(required = false) String stockStatus,
							   Model model) {
		
		List<EnterpriseProductQuantity> quantityList = enterpriseProductService.getQuantityList(searchKey, searchValue, categoryNo, status, stockStatus);
		List<ProductInfoCategory> categoryList = adminProductService.loadCategoryList();
		
		
		 model.addAttribute("title", "보유수량 관리");
		 model.addAttribute("quantityList", quantityList);
		 model.addAttribute("categoryList", categoryList);
		 
		 model.addAttribute("searchKey", searchKey);
		 model.addAttribute("searchValue", searchValue);
		 model.addAttribute("categoryNo", categoryNo);
		 model.addAttribute("status", status);
		 model.addAttribute("stockStatus", stockStatus);
		 
		
		return "enterprise/product/enterpriseQuantityView";
	}
	
	@PostMapping("/modifyQuantity")
	public String modifyQuantity(@RequestParam String productsNo, @RequestParam int quantity, RedirectAttributes redirect) {
	    boolean updated = enterpriseProductService.updateQuantity(productsNo, quantity);
	    redirect.addFlashAttribute("modifyQuantityResult", updated ? "success" : "fail");
	    return "redirect:/enterprise/product/quantityList"; // 목록 페이지로 리다이렉트
	}
	/*
	 * 
	 * 입점업체 상품등록
	 */
	@GetMapping("/addProduct")
	public String enterpriseAddProduct(@RequestParam("productNo") String productNo, Model model) {
		
		AdminProduct product = adminProductService.getProduct(productNo);
	
		List<EnterpriseMarginRatio> marginList = enterpriseMarginRatioService.getEnterpriseMarginRatio();
		List<ProductInfoBenefit> benefitList = adminProductInfoService.getBenefitList();
		
		model.addAttribute("title", "플랫폼 상품목록");
		model.addAttribute("product", product);
		model.addAttribute("marginList", marginList);
		model.addAttribute("benefitList", benefitList);
		
		return "enterprise/product/addSellProductView";
	}
	
	@PostMapping("/addSellProduct")
	@ResponseBody
	public ResponseEntity<?> addSellProduct(
	    @RequestBody EnterpriseSellProductAllRequest allRequest
	) {
		// JSON 가져오기
	    List<EnterpriseSellProductRequest> sellProductRequests = allRequest.getSellProductRequests();
	    EnterpriseProductQuantity quantity = allRequest.getQuantity();

	    // 수량 검증
	    if (quantity == null || quantity.getQuantity() == null || quantity.getQuantity() == 0) {
	        return ResponseEntity.badRequest().body("수량은 필수 입력값입니다.");
	    }

	    try {
	        // 중복체크 및 등록 (중복이면 IllegalStateException 발생)
	        enterpriseProductService.addSellProductBatch(sellProductRequests, quantity);
	        return ResponseEntity.ok("등록 되었습니다");
	    } catch (IllegalStateException e) {
	        // 중복 등록시 메시지 반환
	        return ResponseEntity.badRequest().body(e.getMessage());
	    } catch (Exception e) {
	        e.printStackTrace(); // 콘솔에 에러 전체 로그
	        return ResponseEntity.status(500).body(e.getMessage());
	    }
	}
	
	/*
	 * 입점업체 등록 상품 수정
	 */
	@GetMapping("/modifySellProduct")
	public String showModifySellProductForm(
	        @RequestParam("sellProductsNo") String sellProductsNo, 
	        Model model
	) {

	    EnterpriseProduct product = enterpriseProductService.getProductByNo(sellProductsNo);
	
	    List<EnterpriseProductBenefit> productBenefitList = enterpriseProductService.getBenefitListBySellProductNo(sellProductsNo);
	   
	    EnterprisePenaltyCalculate penalty = enterpriseProductService.getPenaltyCalculateByNo(sellProductsNo);
	    
	    List<ProductInfoBenefit> benefitList = adminProductInfoService.getBenefitList();
	    
	    List<EnterpriseMarginRatio> marginList = enterpriseMarginRatioService.getEnterpriseMarginRatio();

	    model.addAttribute("product", product);
	    model.addAttribute("productBenefitList", productBenefitList);
	    model.addAttribute("penalty", penalty);
	    model.addAttribute("benefitList", benefitList);
	    model.addAttribute("marginList", marginList);

	    return "enterprise/product/modifySellProductView";
	}
	    
	@PostMapping("/modifySellProduct")
	public String modifySellProduct(
	    @ModelAttribute EnterpriseProduct product,
	    @RequestParam("penaltyRatio") double penaltyRatio,
	    @RequestParam("benefitNoList") List<String> benefitNoList,
	    @RequestParam("benefitDetailList") List<String> benefitDetailList,
	    RedirectAttributes redirectAttributes
	) {
	    enterpriseProductService.modifySellProductBenefits(
	    		   product, benefitNoList, benefitDetailList, penaltyRatio
	    );
	    redirectAttributes.addFlashAttribute("msg", "수정 완료!");
	    return "redirect:/enterprise/product/sellProductList";
	}

	// 판매중단 (비활성화)
	@PostMapping("/setSaleStoppage")
	@ResponseBody
	public String setSaleStoppage(@RequestParam("sellProductsNo") String sellProductsNo) {
	    enterpriseProductService.setSaleStoppage(sellProductsNo);
	    return "ok";
	}

	// 판매중단 해제 (활성화)
	@PostMapping("/unsetSaleStoppage")
	@ResponseBody
	public String unsetSaleStoppage(@RequestParam("sellProductsNo") String sellProductsNo) {
	    enterpriseProductService.unsetSaleStoppage(sellProductsNo);
	    return "ok";
	}
	
	
	
}
