package ks54team01.admin.product.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.core.model.Model;
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
	
	@GetMapping("/productList")
	public String productList(Model model) {
		
		return "admin/product/productListView";
	}
	
	@GetMapping("/addProduct")
	public String addProduct(Model model) {
		
		
		return "admin/product/addProductView";
	}
	
	@PostMapping("/addProduct")
	public String addProduct(@RequestPart(name="files1", required = false) MultipartFile[] files1,
			@RequestPart(name="files2", required = false) MultipartFile[] files2, Model model) {
		if(files1 != null) {
			fileService.addFiles(files1, "thumnail");
		}
		if(files2 != null) {
			fileService.addFiles(files2, "product");
		}
		return "admin/product/addProductView";
	}
}
