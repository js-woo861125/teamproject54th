package ks54team01.enterprise.delivery.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import ks54team01.enterprise.delivery.domain.EnterpriseDelivery;
import ks54team01.enterprise.delivery.service.EnterpriseDeliveryService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/enterprise/delivery")
public class EnterpriseDeliveryController {

	private final EnterpriseDeliveryService enterpriseDeliveryService;
	
	
	
	@PostMapping("/departDelivery")
	public String departDelivery(@RequestParam("delInfoNo") String deliveryNo, RedirectAttributes reAttr) {
		
		EnterpriseDelivery delivery = enterpriseDeliveryService.getDeliveryInfoByCode(deliveryNo);
		
		delivery.getAdminDeliveryInfo().setDelCompany("직접배송");
		
		delivery.getAdminDeliveryInfo().setDelProgress("2.배송중");
		
		String currentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		delivery.getAdminDeliveryInfo().setDepartDate(currentDateTime);
		
		enterpriseDeliveryService.modifyDelivery(delivery);
		
		reAttr.addAttribute("delInfoNo", delivery.getAdminDeliveryInfo().getDelInfoNo());
		
		return "redirect:/enterprise/delivery/deliveryList";
	}
	
	
	
	
	
	@PostMapping("/completeDelivery")
	public String completeDelivery(@RequestParam("delInfoNo") String deliveryNo, RedirectAttributes reAttr) {
		
		
		EnterpriseDelivery delivery = enterpriseDeliveryService.getDeliveryInfoByCode(deliveryNo);
		
		delivery.getAdminDeliveryInfo().setDelProgress("3.배송완료(설치완료)");
		
		String currentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		delivery.getAdminDeliveryInfo().setArriveDate(currentDateTime);
		
		enterpriseDeliveryService.modifyDelivery(delivery);
		
		reAttr.addAttribute("delInfoNo", delivery.getAdminDeliveryInfo().getDelInfoNo());
		
		return "redirect:/enterprise/delivery/deliveryList";
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
