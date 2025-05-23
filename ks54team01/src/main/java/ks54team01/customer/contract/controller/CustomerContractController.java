package ks54team01.customer.contract.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import ks54team01.customer.contract.domain.CustomerContract;
import ks54team01.customer.contract.service.CustomerContractService;
import ks54team01.customer.member.domain.CustomerMember;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/contract")
public class CustomerContractController {

	
	private final CustomerContractService customerContractService;
	
	
	@PostMapping("/addContract")
	@ResponseBody
	public String addContract(@RequestParam("sellProductsNo") String sellProductsNo, @RequestParam("entCeoNo") String entCeoNo
							, @RequestParam("entEmpId") String entEmpId, @RequestParam("period") Integer period
							, @RequestParam("orderQuantity") Integer orderQuantity, Model model, HttpSession session) {
		
		CustomerMember loginMember = (CustomerMember) session.getAttribute("loginMember");
        String custId = loginMember.getMemberId();
        
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
}
