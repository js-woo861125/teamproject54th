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
    
//	@Value("${file.path:/}") // 
//    private String uploadDir;
	
	
	
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
	
	
	/**
	 * 상품 ID (productsNum)를 통해 이미지 데이터를 스트리밍하여 반환합니다.
	 * 이 메서드는 Thymeleaf 템플릿의 <img> 태그 src 속성에서 호출됩니다.
	 * 예: <img th:src="@{/customer/product/image/{productsNum}(productsNum=${product.productsNum})}" />
	 * (클래스 레벨 @RequestMapping("/customer/product")에 의해 실제 URL은 /customer/product/image/{productsNum}이 됨)
	 *
	 * @param productsNum 조회할 상품의 productsNum (URL 경로 변수로 받음)
	 * @return 이미지 데이터와 MIME 타입이 포함된 ResponseEntity (HTTP 응답)
	 */
//	@GetMapping("/image/{productsNum}")
//	public ResponseEntity<Resource> getProductImage(@PathVariable("productsNum") String productsNum) {
//		
//		List<CustomerProduct> productList = customerProductService.getProductDetailByProd(productsNum);
//
//		CustomerProduct product = null;
//		if (productList != null && !productList.isEmpty()) {
//			product = productList.get(0); // 첫 번째 상품 정보 가져오기
//		}
//
//		// imageFilePath 필드를 사용하도록 수정
//		if (product != null && product.getImageFilePath() != null && !product.getImageFilePath().isEmpty()) {
//			try {
//                // 파일 경로 생성: 기본 업로드 디렉토리 + DB에서 가져온 파일 경로
//                // 예: /home/teamproject/attachment/20250522/image/product/2318b7ab-72f9-4c39-bc05-6cb158180c75.jpg
//                // DB의 file_path가 '/attachment/...'와 같이 이미 uploadDir의 상대 경로라면 resolve() 사용
//                // 만약 DB의 file_path가 이미 '/home/teamproject/attachment/...'와 같은 완전한 절대 경로라면,
//                // Paths.get(product.getImageFilePath()).normalize()만 사용하고 uploadDir은 필요 없습니다.
////				Path filePath = Paths.get(uploadDir).resolve(product.getImageFilePath()).normalize();
//				Path filePath = Paths.get(product.getImageFilePath()).normalize();
//                log.info("이미지 로드를 시도하는 최종 파일 경로: {}", filePath.toString());
//
//                Resource resource = new UrlResource(filePath.toUri());
//
//                // 파일이 존재하고 읽을 수 있는지 확인
//                if (resource.exists() && resource.isReadable()) {
//                    // 이미지 MIME 타입 가져오기 (CustomerProduct에서 가져온 imageMimeType 사용)
//                    String contentType = product.getImageType();
//                    if (contentType == null || contentType.isEmpty()) {
//                        // MIME 타입이 없으면 기본값 또는 파일 확장자로 유추
//                        contentType = Files.probeContentType(filePath); // 파일 확장자로 MIME 타입 유추
//                        if (contentType == null) {
//                            contentType = "application/octet-stream"; // 알 수 없는 타입
//                        }
//                    }
//
//                    return ResponseEntity.ok()
//                            .contentType(MediaType.parseMediaType(contentType))
//                            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
//                            .body(resource);
//                } else {
//                    log.warn("이미지 파일을 찾을 수 없거나 읽을 수 없습니다: {}", filePath);
//                    return ResponseEntity.notFound().build();
//                }
//			} catch (IOException e) {
//				log.error("이미지 파일을 읽는 중 오류 발생: productsNum = {}", productsNum, e);
//				return ResponseEntity.internalServerError().build(); // 500 Internal Server Error
//			}
//		}
//		// 상품을 찾을 수 없거나 이미지 파일 경로가 없는 경우 404 Not Found 반환
//		return ResponseEntity.notFound().build();
//	}

	
	
	
}
