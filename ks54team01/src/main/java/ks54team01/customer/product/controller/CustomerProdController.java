package ks54team01.customer.product.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks54team01.customer.product.domain.CustomerProduct;
import ks54team01.customer.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/customer/prod")
@RequiredArgsConstructor
@Slf4j
public class CustomerProdController {

	private final ProductMapper productMapper;
	
	
	@GetMapping("/prodDetail")
	public String getProdDetail(Model model) {
		
		String prod = "prod_1";
		
		List<CustomerProduct> customerProduct =  productMapper.getProductDetailByProd(prod);
		
		String prodNm = customerProduct.get(0).getProductsName();
		
		model.addAttribute("customerProduct", customerProduct);
		model.addAttribute("prodNm", prodNm);
		
		return "customer/product/prodDetailView";
	}
	
	
}
