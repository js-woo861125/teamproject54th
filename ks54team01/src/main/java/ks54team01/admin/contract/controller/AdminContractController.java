package ks54team01.admin.contract.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks54team01.admin.contract.domain.AdminContract;
import ks54team01.admin.contract.service.AdminContractService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/contract")
public class AdminContractController {

	private final AdminContractService adminContractService;
	
	
	
	@GetMapping("/searchContractList")
	public String getSearchContractList(String searchKey, String searchValue, Model model) {
		
		List<AdminContract> ContractList = adminContractService.getSearchContractList(searchKey, searchValue);
		
		model.addAttribute("title", "입점업체&고객 계약목록");
		model.addAttribute("ContractList", ContractList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		
		return "admin/contract/ContractListView";
	}
	
	
	@GetMapping("/contractList")
	public String getContractList(Model model) {
		
		List<AdminContract> ContractList = adminContractService.getContractList();
		
		model.addAttribute("title", "입점업체&고객 계약목록");
		model.addAttribute("ContractList", ContractList);
		
		return "admin/contract/ContractListView";
	}
	
	
}
