package ks54team01.customer.delivery.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import ks54team01.customer.delivery.domain.CustomerDeliveryList;
import ks54team01.customer.delivery.service.CustomerDeliveryService;
import ks54team01.customer.member.domain.CustomerMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/customer/delivery")
@RequiredArgsConstructor
@Slf4j
public class CustomerDeliveryController {

	private final CustomerDeliveryService customerDeliveryService;

	
	
	@PostMapping("/removeDeliveryList")
	@ResponseBody
	public boolean removeDeliveryList(@RequestParam(name="delNo") String delNo) {
		
		boolean isDel = customerDeliveryService.removeDeliveryList(delNo);
		
		return isDel;
	}
	
	
	@PostMapping("/modifyDeliveryList")
	public String modifyDeliveryList(CustomerDeliveryList modifyDeliveryList) {
		
		customerDeliveryService.modifyDeliveryList(modifyDeliveryList);
		
		
		return "redirect:/customer/delivery/deliveryList";
	}
	
	
	
	@PostMapping("/addDeliveryList")
	public String addDeliveryList(CustomerDeliveryList customerDeliveryList, HttpSession session) {
		
		String custId = (String) session.getAttribute("loginId");
		
		customerDeliveryList.setCustId(custId);
		
		customerDeliveryService.addDeliveryList(customerDeliveryList);
		
		
		return "redirect:/customer/delivery/deliveryList";
	}
	
	
	@GetMapping("/deliveryList")
	public String getDeliveryList(HttpSession session, Model model) {
		
		String custId = (String) session.getAttribute("loginId");
		
		log.info("회원아이디 : {}", custId);
		
		List<CustomerDeliveryList> deliveryList = customerDeliveryService.getDeliveryList(custId);
		
		model.addAttribute("title", "배송지 목록");
		model.addAttribute("deliveryList", deliveryList);
		
		
		return "customer/myPage/myDeliveryListView";
		
	}
	
	
	
	
	
}
