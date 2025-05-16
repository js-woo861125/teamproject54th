package ks54team01.customer.delivery.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks54team01.customer.delivery.domain.CustomerDeliveryList;
import ks54team01.customer.delivery.service.CustomerDeliveryService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/customer/delivery")
@RequiredArgsConstructor
public class CustomerDeliveryController {

	private final CustomerDeliveryService customerDeliveryService;

	
	@PostMapping("/addDeliveryList")
	public String addDeliveryList(CustomerDeliveryList customerDeliveryList) {
		
		customerDeliveryService.addDeliveryList(customerDeliveryList);
		
		
		return "redirect:/customer/delivery/deliveryList";
	}
	
	
	@GetMapping("/deliveryList")
	public String getDeliveryList(Model model) {
		
		List<CustomerDeliveryList> DeliveryList = customerDeliveryService.getDeliveryList();
		
		model.addAttribute("title", "배송지 목록");
		model.addAttribute("modalTitle", "배송지 등록");		
		model.addAttribute("DeliveryList", DeliveryList);
		
		
		return "customer/myPage/myDeliveryListView";
		
	}
	
	
	
	
	
}
