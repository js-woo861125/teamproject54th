package ks54team01.admin.contract.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks54team01.admin.contract.domain.AdminContract;
import ks54team01.admin.contract.domain.AdminContractDetail;
import ks54team01.admin.contract.service.AdminContractService;
import ks54team01.admin.contract.service.impl.AdminContractServiceImpl;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/contract")
public class AdminContractDetailController {
	
	private final AdminContractService adminContractService;
	
	@GetMapping("/contractDetail")
	public String getContractDetail(@RequestParam(value = "rentalContNo") String rentalContNo, Model model) {
		
		List<AdminContractDetail> contractDetailList = adminContractService.getContractDetail(rentalContNo);
		
		model.addAttribute("title", "계약상세");
		model.addAttribute("contractDetailList", contractDetailList);
		
		return "admin/contract/contractDetail";
	}
	
}
