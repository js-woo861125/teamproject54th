package ks54team01.enterprise.rentalCancel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/enterprise/rentalCancel")
@RequiredArgsConstructor
public class EnterpriseRentalCancelController {

	@GetMapping("/rentalCancelList")
	public String getRentalCancelList() {
		
		return "enterprise/request/rentalCancelRequestListView";
	}
}
