package ks54team01.enterprise.delivery.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ks54team01.enterprise.delivery.domain.EnterpriseDelivery;
import ks54team01.enterprise.delivery.service.EnterpriseDeliveryService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/enterprise/delivery")
public class EnterpriseDeliveryController {

	private final EnterpriseDeliveryService enterpriseDeliveryService;
	
	
	
	
	@PostMapping("/modifyDelivery")
	public String modifyDelivery(EnterpriseDelivery enterpriseDelivery, RedirectAttributes reAttr) {
		
		enterpriseDeliveryService.modifyDelivery(enterpriseDelivery);
		
		reAttr.addAttribute("delInfoNo", enterpriseDelivery.getAdminDeliveryInfo().getDelInfoNo());
		
		return "redirect:/enterprise/delivery/modifyDeliveryView";
	}
	
	
	
	@GetMapping("/modifyDelivery")
	public String modifyDelivery(String delInfoNo, Model model) {
		
		EnterpriseDelivery deliveryInfo = enterpriseDeliveryService.getDeliveryInfoByCode(delInfoNo);
		
		model.addAttribute("deliveryInfo", deliveryInfo);
		
		return "enterprise/delivery/modifyDeliveryView";
	}
	
	
	
	@GetMapping("/searchDeliveryList")
	public String getSearchDeliveryList(String searchKey, String searchValue, Model model) {
		
		List <EnterpriseDelivery> DeliveryList = enterpriseDeliveryService.getSearchDeliveryList(searchKey, searchValue);
		
		model.addAttribute("title", "배송정보 목록");
		model.addAttribute("DeliveryList", DeliveryList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		
		return "enterprise/delivery/deliveryListView";
	}
	
	
	@GetMapping("/deliveryList")
	public String getDeliveryList(Model model) {
		
		List <EnterpriseDelivery> DeliveryList = enterpriseDeliveryService.getDeliveryList();
		
		model.addAttribute("title", "배송정보 목록");
		model.addAttribute("DeliveryList", DeliveryList);
		
		return "enterprise/delivery/deliveryListView";
	}
}
