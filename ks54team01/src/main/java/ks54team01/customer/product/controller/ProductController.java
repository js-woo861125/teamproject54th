package ks54team01.customer.product.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.admin.productInfo.mapper.AdminProductInfoMapper;
import ks54team01.customer.product.domain.BenefitDetail;
import ks54team01.customer.product.domain.CustomerProduct;
import ks54team01.customer.product.service.CustomerProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/product")
@Slf4j
public class ProductController {

	private final CustomerProductService customerProductService;
	

	
	// 이미지 파일이 저장된 기본 경로 (application.properties 등에서 설정)
    
	@Value("${file.path:/}") // 
    private String uploadDir;
	
	

	@GetMapping("/productDetailByProd")
	public String getProductDetailByProd(@RequestParam(value="productsNum") String productsNum,
										@ModelAttribute("addResult") String addResult, 
										Model model) {
		
		
		
		// 서비스 계층을 통해 상품 상세 정보를 productsNum으로 조회 (List<CustomerProduct> 반환)
		List<CustomerProduct> productDetailByProd = customerProductService.getProductDetailByProd(productsNum);
//		productDetailByProd.get(0).ent
//		for(EntCeo newEntCeoArr : productDetailByProd.get(0).getEntCeo()) {
//			
//		}
//		
		List<BenefitDetail> benefit = customerProductService.getBenefitByProduct(productsNum);
		
		String prodNm = productDetailByProd.get(0).getProductsName();
		String image = productDetailByProd.get(0).getImageFilePath();
		String prodDetail = productDetailByProd.get(0).getProdDetail();
		
		CustomerProduct checkPrice = customerProductService.getLowerPriceAndMaxPeriod(productsNum);
		String lowerPrice = checkPrice.getMinRentalPrice();
		String maxPeriod = checkPrice.getMaxPeriod();
		
		// 로그에 조회된 상품 상세 정보 출력
		log.info("productDetailByProd:{}", productDetailByProd);
		log.info("benefit: {}", benefit);
		
		
		List<CustomerProduct> productSpec = customerProductService.getProductSpecList(productsNum);
		
		
		
		// 모델에 상품 상세 정보 리스트 추가
		model.addAttribute("productDetailByProd", productDetailByProd);
		model.addAttribute("prodNm", prodNm);
		model.addAttribute("lowerPrice", lowerPrice);
		model.addAttribute("maxPeriod", maxPeriod);
		model.addAttribute("productSpec" ,productSpec);
		model.addAttribute("addResult", addResult);
		model.addAttribute("benefit", benefit);
		model.addAttribute("image", image);
		model.addAttribute("prodDetail", prodDetail);
		
		return "customer/product/prodDetailView";
	}
	
	

	
	/**
	 * 특정 소분류 카테고리에 해당하는 상품 목록을 조회하여 반환합니다.
	 * URL: /customer/product/productList
	 *
	 * @param smallCategory 조회할 소분류 카테고리 (URL 쿼리 파라미터로 받음)
	 * @param model         Thymeleaf 템플릿으로 데이터를 전달하기 위한 Model 객체
	 * @return 상품 목록을 표시할 Thymeleaf 템플릿 경로 ("customer/product/productList")
	 */
	
	
	@GetMapping("/productList")
	public String getProductList(@RequestParam(name="smallCategory") String smallCategory, Model model) {
		
		// 서비스 계층을 통해 특정 소분류 카테고리의 상품 목록 조회
		List<CustomerProduct> productList = customerProductService.getCustomerProductList(smallCategory);
	
		// 모델에 페이지 제목 (소분류 카테고리 이름) 추가
		model.addAttribute("title", smallCategory);
		// 모델에 상품 목록 리스트 추가
		model.addAttribute("productList", productList);
	
		// 로그에 소분류 카테고리와 조회된 상품 목록 출력
		log.info("smallCategory : {}", smallCategory);
		log.info("customerProduct: {}", productList);

		
		
		return "customer/product/productList";
	}
	
	
}
