package ks54team01.admin.delivery.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks54team01.admin.delivery.domain.AdminDelivery;
import ks54team01.admin.delivery.domain.AdminDeliveryInfo;
import ks54team01.admin.delivery.service.AdminDeliveryService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/delivery")
public class AdminDeliveryController {
	
	private final AdminDeliveryService adminDeliveryService;
	
	
	
	@GetMapping("/searchDeliveryInfoList")
	public String getSearchDeliveryInfoList(String searchKey, String searchValue, Model model) {
		
		List <AdminDeliveryInfo> DeliveryInfoList = adminDeliveryService.getSearchDeliveryInfoList(searchKey, searchValue);
		
		model.addAttribute("title", "배송정보 목록");
		model.addAttribute("DeliveryInfoList", DeliveryInfoList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		
		return "admin/deliveryInfo/deliveryInfoListView";
	}
	
	
	@GetMapping("/deliveryInfoList")
	public String getDeliveryInfoList(Model model) {
		
		List <AdminDeliveryInfo> DeliveryInfoList = adminDeliveryService.getDeliveryInfoList();
		
		model.addAttribute("title", "배송정보 목록");
		model.addAttribute("DeliveryInfoList", DeliveryInfoList);
		
		return "admin/deliveryInfo/deliveryInfoListView";
	}
	
	
	
	@GetMapping("/searchDeliveryList")
	public String getSearchDeliveryList(String searchKey, String searchValue, Model model) {
		
		List<AdminDelivery> DeliveryList = adminDeliveryService.getSearchDeliveryList(searchKey, searchValue);
		
		model.addAttribute("title", "배송지 목록");
		model.addAttribute("DeliveryList", DeliveryList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		return "admin/delivery/deliveryListView";
	}
	
	
	// 배송지 조회
	@GetMapping("/deliveryList")
	public String getDeliveryList(Model model) {
		
		List<AdminDelivery> DeliveryList = adminDeliveryService.getDeliveryList();
		
		model.addAttribute("title", "배송지 목록");
		model.addAttribute("DeliveryList", DeliveryList);
		
		return "admin/delivery/deliveryListView";
		
	}
	
	

}
