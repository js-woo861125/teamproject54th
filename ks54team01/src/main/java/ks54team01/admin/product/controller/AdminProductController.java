package ks54team01.admin.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import ks54team01.admin.product.domain.AdminProduct;
import ks54team01.admin.product.domain.AdminProductSpecContent;
import ks54team01.admin.product.mapper.AdminProductMapper;
import ks54team01.admin.product.service.AdminProductService;
import ks54team01.admin.productInfo.domain.ProductInfoBrand;
import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.admin.productInfo.domain.ProductInfoItem;
import ks54team01.admin.productInfo.domain.ProductInfoModel;
import ks54team01.common.file.domain.FileMetaData;
import ks54team01.common.file.service.FileService;
import ks54team01.common.file.util.FilesUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/product")
@Slf4j
public class AdminProductController {
	
	@Value("${file.path}")
	private String fileRealPath;
	
	@Autowired
	private final FilesUtils filesUtils;
	
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
	    List<FileMetaData> mainImageList = fileService.getFileList(product.getModelNo(), "mainImage");
	    List<FileMetaData> thumbnailList = fileService.getFileList(product.getModelNo(), "thumbnail");
	    List<AdminProductSpecContent> specContentList = adminProductMapper.loadSpecContent(product.getModelNo());
	    
	    
	    model.addAttribute("titlt", "상품수정");
	    model.addAttribute("product", product);
	    model.addAttribute("categoryList", categoryList);
	    model.addAttribute("itemList", itemList);
	    model.addAttribute("brandList", brandList);
	    model.addAttribute("modelList", modelList);
	    model.addAttribute("mainImageList", mainImageList);
	    model.addAttribute("thumbnailList", thumbnailList);
	    model.addAttribute("specContentList", specContentList);
	   
	    return "admin/product/modifyProductView";
	}
	
	
	@PostMapping("/modifyProduct")
	public String modifyProduct(
	    AdminProduct product,
	    @RequestParam(value = "mainImage", required = false) MultipartFile[] mainImage,
	    @RequestParam(value = "thumbnails", required = false) MultipartFile[] thumbnails,
	    @RequestParam(value = "deleteFileIdxs", required = false) String deleteFileIdxs) {

		adminProductService.modifyProduct(product, mainImage, thumbnails, deleteFileIdxs);
		
	    return "redirect:/admin/product/productList";
	}
	
	// 상품 조회 + 검색기능
	@GetMapping("/productList")
	public String getProductList(@RequestParam(required = false) String searchKey,
								 @RequestParam(required = false) String searchValue,
								 @RequestParam(required = false) String categoryNo,
								 @RequestParam(required = false) String status,
								 Model model) {
		List<ProductInfoCategory> categoryList = adminProductService.loadCategoryList();
		List<AdminProduct> adminProductList = adminProductService.searchProductList(searchKey, searchValue, categoryNo, status);
	  
		
		model.addAttribute("adminProductList", adminProductList);		
		model.addAttribute("title", "등록상품리스트");
		model.addAttribute("adminProductList", adminProductList);
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("categoryNo", categoryNo);
		model.addAttribute("status", status);
		
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
	    @RequestParam("productsDetail") String productsDetail
	) {
	    AdminAddProduct addProduct = new AdminAddProduct();
	    addProduct.setCategoryNo(categoryNo);
	    addProduct.setItemNo(itemNo);
	    addProduct.setBrandNo(brandNo);
	    addProduct.setModelNo(modelNo);
	    addProduct.setProductName(productName);
	    addProduct.setProductDetail(productsDetail);

	  
	    adminProductService.addProduct(addProduct, mainImage, thumbnails);

	    return "redirect:/admin/product/productList";
	}
	
	@PostMapping("/uploadImage")
	@ResponseBody
	public Map<String, Object> uploadImage(@RequestParam("upload") MultipartFile multipartFile){
		
		Map<String, Object> response = new HashMap<String, Object>(); 
		FileMetaData fileInfo = filesUtils.uploadFile(multipartFile);
		
		if(fileInfo != null) {	
			response.put("url", fileInfo.getFilePath());
			response.put("uploaded", "1");
			response.put("fileName", fileInfo.getFileOriginalName());
		}else {
			Map<String, Object> error = new HashMap<String, Object>();
			error.put("message", "파일이미지 업로드 실패");
			response.put("uploaded", "0");
			response.put("error", error);
		}
		
		return response;
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
	
	// 등록상품 판매중단
	@PostMapping("/setSaleStoppage")
	@ResponseBody
	public String saleStoppage(@RequestParam("productNo")String productNo) {
		adminProductService.setSaleStoppage(productNo);
		
		return "ok";
	}
	// 등록상품 판매중단 해제
	@PostMapping("/unsetSaleStoppage")
	@ResponseBody
	public String unsetSaleStoppage(@RequestParam("productNo") String productNo) {
	    adminProductService.unsetSaleStoppage(productNo);
	    return "ok";
	}
	
}
