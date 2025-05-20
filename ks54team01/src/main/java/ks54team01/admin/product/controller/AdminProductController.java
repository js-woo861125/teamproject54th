package ks54team01.admin.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ks54team01.admin.product.domain.AdminAddProduct;
import ks54team01.admin.product.domain.AdminProduct;
import ks54team01.admin.product.domain.AdminProductSpecContent;
import ks54team01.admin.product.mapper.AdminProductMapper;
import ks54team01.admin.product.service.AdminProductService;
import ks54team01.admin.productInfo.domain.ProductInfoBrand;
import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.admin.productInfo.domain.ProductInfoItem;
import ks54team01.admin.productInfo.domain.ProductInfoModel;
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
	private final AdminProductService adminProductService;
	private final AdminProductMapper adminProductMapper;
		
	
	@GetMapping("/modifyProduct")
	public String modifyProduct(@RequestParam("productNo") String productNo, Model model) {
	    
	    AdminProduct product = adminProductService.getProduct(productNo);

	  
	    List<ProductInfoCategory> categoryList = adminProductService.loadCategoryList();
	    List<ProductInfoItem> itemList = adminProductService.loadItemList(product.getCategoryNo());
	    List<ProductInfoBrand> brandList = adminProductService.loadBrandList(product.getCategoryNo(), product.getItemNo());
	    List<ProductInfoModel> modelList = adminProductService.loadModelList(product.getCategoryNo(), product.getItemNo(), product.getBrandNo());

	 
	    model.addAttribute("product", product);
	    model.addAttribute("categoryList", categoryList);
	    model.addAttribute("itemList", itemList);
	    model.addAttribute("brandList", brandList);
	    model.addAttribute("modelList", modelList);

	   
	    return "admin/product/modifyProductView";
	}
	
		@PostMapping("/modifyProduct")
		public String modifyProduct(AdminProduct product,
		                            @RequestParam("mainImage") MultipartFile[] mainImage,
		                            @RequestParam("thumbnails") MultipartFile[] thumbnails,
		                            @RequestParam("details") MultipartFile[] details) {
		   
		//	adminProductService.modifyProduct(product, mainImage, thumbnails, details);
		    return "redirect:/admin/product/productList";
		}
	
		@GetMapping("/productList")
	public String getProductList(Model model) {
		List<AdminProduct> adminProductList = adminProductService.getProductList();
		
		model.addAttribute("title", "등록상품리스트");
		model.addAttribute("adminProductList", adminProductList);
		
		return "admin/product/productListView";
	}
	
	
	
	@GetMapping("/addProduct")
	public String addProduct(Model model) {
		
		
		return "admin/product/addProductView";
	}
	
	@PostMapping("/addProduct")
	public String addProduct(
	    @RequestParam("categoryNo") String categoryNo,
	    @RequestParam("itemNo") String itemNo,
	    @RequestParam("brandNo") String brandNo,
	    @RequestParam("modelNo") String modelNo,
	    @RequestParam("productName") String productName,
	    @RequestParam("mainImage") MultipartFile[] mainImage,
	    @RequestParam("thumbnails") MultipartFile[] thumbnails,
	    @RequestParam("details") MultipartFile[] details
	) {
	    AdminAddProduct addProduct = new AdminAddProduct();
	    addProduct.setCategoryNo(categoryNo);
	    addProduct.setItemNo(itemNo);
	    addProduct.setBrandNo(brandNo);
	    addProduct.setModelNo(modelNo);
	    addProduct.setProductName(productName);

	  
	    adminProductService.addProduct(addProduct, mainImage, thumbnails, details);

	    return "redirect:/admin/product/productList";
	}
	
	
	@GetMapping("/categoryList")
	@ResponseBody
	public List<ProductInfoCategory> loadCategoryList() {
	    return adminProductService.loadCategoryList();
	}

	@GetMapping("/itemList")
	@ResponseBody
	public List<ProductInfoItem> loadItemList(@RequestParam String categoryNo) {
	    return adminProductService.loadItemList(categoryNo);
	}

	@GetMapping("/brandList")
	@ResponseBody
	public List<ProductInfoBrand> loadBrandList(@RequestParam String categoryNo, @RequestParam String itemNo) {
	    return adminProductService.loadBrandList(categoryNo, itemNo);
	}

	@GetMapping("/modelList")
	@ResponseBody
	public List<ProductInfoModel> loadModelList(@RequestParam String categoryNo,
	                                            @RequestParam String itemNo,
	                                            @RequestParam String brandNo) 
	{
	    return adminProductService.loadModelList(categoryNo, itemNo, brandNo);
	}
	
	@GetMapping("/specContent")
	@ResponseBody
	public List<AdminProductSpecContent> loadSpecContent(@RequestParam String modelNo) {
	    return adminProductMapper.loadSpecContent(modelNo);
	}
	

	
	
}
