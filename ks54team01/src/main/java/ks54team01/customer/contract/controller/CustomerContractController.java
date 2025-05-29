package ks54team01.customer.contract.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import ks54team01.admin.contract.domain.AdminContractDetail;
import ks54team01.admin.contract.service.AdminContractService;
import ks54team01.customer.contract.domain.CustomerContract;
import ks54team01.customer.contract.service.CustomerContractService;
import ks54team01.customer.member.domain.CustomerMember;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/contract")
public class CustomerContractController {

	
	private final CustomerContractService customerContractService;
	private final AdminContractService adminContractService;
	
	
	@PostMapping("/addContract")
	@ResponseBody
	public String addContract(@RequestParam("sellProductsNo") String sellProductsNo, @RequestParam("entCeoNo") String entCeoNo
							, @RequestParam("entEmpId") String entEmpId, @RequestParam("period") Integer period
							, @RequestParam("orderQuantity") Integer orderQuantity, Model model, HttpSession session) {
		
		String custId = (String) session.getAttribute("loginId");
        
        CustomerContract customerContract = new CustomerContract();
        
        customerContract.setEntCeoNo(entCeoNo);
        customerContract.setEntEmpId(entEmpId);
        customerContract.setCustId(custId);
        customerContract.setSellProdNo(sellProductsNo);
        customerContract.setContractPeriod(period);
        customerContract.setContractQuantity(orderQuantity);
		
		customerContractService.addCustomerContract(customerContract);
		
		String rentalContractNo = customerContract.getRentalContractNo();
		
		return rentalContractNo;
	}
	
	//계약리스트 조회
	@GetMapping("/myContractList")
	public String contractList(@RequestParam(value = "searchKey", required = false) String searchKey,
	        				HttpSession session, Model model) {
		
		String custId = (String) session.getAttribute("loginId");
		
		List<CustomerContract> customerContractList = customerContractService.myCustomerContractList(custId, searchKey);
		
		
		model.addAttribute("customerContractList",customerContractList);
		model.addAttribute("searchKey", searchKey);
		
		return "customer/myPage/myContractListView";
	}
	
	// 고객 계약리스트 상세
	
		@GetMapping("/contractDetails")
		public String getContractDetail(@RequestParam(value = "rentalContNo") String rentalContNo, Model model) {
			
			List<AdminContractDetail> ContractDetailList = adminContractService.getContractDetail(rentalContNo);
			
			model.addAttribute("title", "계약상세");
			model.addAttribute("ContractDetailList", ContractDetailList);
			
			return "customer/myPage/myContractDetail";
		}
	
	
}
