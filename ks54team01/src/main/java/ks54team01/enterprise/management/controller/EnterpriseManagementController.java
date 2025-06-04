package ks54team01.enterprise.management.controller;

import java.text.NumberFormat;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import ks54team01.customer.member.domain.EntMember;
import ks54team01.enterprise.management.domain.EnterpriseContractInfo;
import ks54team01.enterprise.management.service.EnterpriseManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/enterprise/management")
@Slf4j
public class EnterpriseManagementController {
	
	private final EnterpriseManagementService enterpriseManagementService;
	
	@GetMapping("/contractInfo")
	public String contractInfo(HttpSession session, Model model) {
		String entCeoNo = (String) session.getAttribute("entCeoNo");
		EnterpriseContractInfo contractInfo = enterpriseManagementService.getEntContractInfo(entCeoNo);
		
		// 금액 포맷
		NumberFormat numberFormat = NumberFormat.getInstance(); 
		String formattedEntryFee = numberFormat.format(contractInfo.getEntryFee()); // 입점비
		
		model.addAttribute("title", "플랫폼 계약정보");
		model.addAttribute("contractInfo", contractInfo);
		model.addAttribute("formattedEntryFee", formattedEntryFee);
		
		return "enterprise/management/contractInfoView";
	}

	@PostMapping("/addEmployee")
	public String addMember(HttpSession session, EntMember entMember) {
		
		log.info("직원등록요청: {}", entMember);
		
		String entCeoNo = (String) session.getAttribute("entCeoNo");
		String entBrno = (String) session.getAttribute("entBrno");
		String entName = (String) session.getAttribute("entName");
		entMember.setEntCeoNo(entCeoNo);
		entMember.setEntBrno(entBrno);
		entMember.setEntName(entName);
		
		
		enterpriseManagementService.addEntEmpMember(entMember);
		
		log.info("직원등록 성공: {}", entMember);
		
		return "redirect:/enterprise/management/employeeList";
	}
	
	@GetMapping("/addEmployee")
	public String addEmployee(Model model) {
		
		model.addAttribute("title", "직원등록");
		
		return "enterprise/management/addEmployeeView";
	}

	@GetMapping("/employeeList") 
	public String getEmployeeList(HttpSession session, Model model) {
		String entCeoNo = (String) session.getAttribute("entCeoNo");
		
		List<EntMember> employeeList = enterpriseManagementService.getEmployeeList(entCeoNo);
		log.info("employeeList: {}", employeeList);
		
		
		model.addAttribute("title", "직원목록");
		model.addAttribute("employeeList", employeeList);
		
		
		return "enterprise/management/employeeListView";
	}
	
}
