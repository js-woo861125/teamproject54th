package ks54team01.admin.product.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ks54team01.admin.product.domain.AdminAddProduct;
import ks54team01.admin.product.service.AdminProductService;
import ks54team01.admin.productInfo.service.AdminProductInfoService;
import ks54team01.common.file.mapper.FileMapper;
import ks54team01.common.file.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/product")
@Slf4j
public class AdminProductController {
	@Value("${file.path}")
	private String fileRealPath;
	
	
	
	private final FileMapper fileMapper;
	private final FileService fileService;
	private final AdminProductInfoService adminProductInfoservice;
	private final AdminProductService adminProductService;
		
	
	@GetMapping("/productList")
	public String productList(Model model) {
		
		model.addAttribute("title", "등록상품리스트");
		
		return "admin/product/productListView";
	}
	
	@GetMapping("/addProduct")
	public String addProduct(Model model) {
		
		
		return "admin/product/addProductView";
	}
	
	/*
	 * @PostMapping("/addProduct") public String
	 * addProduct(@RequestPart(name="files1", required = false) MultipartFile[]
	 * files1,
	 * 
	 * @RequestPart(name="files2", required = false) MultipartFile[] files2, Model
	 * model) { if(files1 != null) { fileService.addFiles(files1, "thumnail"); }
	 * if(files2 != null) { fileService.addFiles(files2, "product"); } return
	 * "admin/product/addProductView"; }
	 */
	
	@PostMapping("/product/register")
	@ResponseBody
	public String registerProduct(
	    @RequestParam("modelNo") String modelNo,
	    @RequestParam("productName") String productName,
	    @RequestParam("thumbnails") MultipartFile[] thumbnails,
	    @RequestParam("details") MultipartFile[] details) 
	{
	    AdminAddProduct addProduct = new AdminAddProduct();
	    addProduct.setModelNo(modelNo);
	    addProduct.setProductName(productName);

	    adminProductService.registerProduct(addProduct, thumbnails, details);
	    return "success";
	}
	
	
}
