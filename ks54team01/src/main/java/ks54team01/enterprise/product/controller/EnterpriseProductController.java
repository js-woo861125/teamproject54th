package ks54team01.enterprise.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ks54team01.admin.product.domain.AdminProduct;
import ks54team01.admin.product.service.AdminProductService;
import ks54team01.admin.productInfo.domain.ProductInfoBenefit;
import ks54team01.admin.productInfo.service.AdminProductInfoService;
import ks54team01.enterprise.product.domain.EnterpriseMarginRatio;
import ks54team01.enterprise.product.domain.EnterpriseProduct;
import ks54team01.enterprise.product.domain.EnterpriseProductQuantity;
import ks54team01.enterprise.product.domain.EnterpriseSellProductRequest;
import ks54team01.enterprise.product.mapper.EnterpriseMarginRatioMapper;
import ks54team01.enterprise.product.service.EnterpriseMarginRatioService;
import ks54team01.enterprise.product.service.EnterpriseProductService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/enterprise/product")
public class EnterpriseProductController {
	
	
	@Value("${file.path}")
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
	public String getsellProductList(Model model) {
		List<EnterpriseProduct> sellProductList = enterpriseProductService.getSellProductList();
		
		model.addAttribute("title", "판매 상품목록");
		model.addAttribute("sellProductList", sellProductList);
		
		return "enterprise/product/enterpriseProductListView";
	}
	/*
	 * 플랫폼 등록 상품 리스트
	 */
	@GetMapping("/productList")
	public String getproductList(Model model) {
		List<AdminProduct> getProductList = enterpriseProductService.getProductList();
		
		model.addAttribute("title", "플랫폼 상품목록");
		model.addAttribute("getProductList", getProductList);
		
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
	public String quantityList(Model model) {
		
		List<EnterpriseProductQuantity> quantityList = enterpriseProductService.getQuantityList();
		
		model.addAttribute("title", "재고관리");
		model.addAttribute("quantityList", quantityList);
		
		return "enterprise/product/enterpriseQuantityView";
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
		
		model.addAttribute("product", product);
		model.addAttribute("marginList", marginList);
		model.addAttribute("benefitList", benefitList);
		
		return "enterprise/product/addSellProductView";
	}
	
	@PostMapping("/addSellProduct")
	public String addSellProduct(
	    @RequestBody List<EnterpriseSellProductRequest> sellProductRequests,
	    @ModelAttribute EnterpriseProductQuantity quantity
	) {
	    if (quantity.getQuantity() == null || quantity.getQuantity() == 0) {
	        throw new IllegalArgumentException("수량은 필수 입력값입니다.");
	    }

	    enterpriseProductService.addSellProductBatch(sellProductRequests, quantity);

	    return "redirect:/enterprise/product/sellProductList";
	}
	
}
