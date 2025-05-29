package ks54team01.admin.enterprise.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import ks54team01.admin.enterprise.domain.AdminEntAddContract;
import ks54team01.admin.enterprise.domain.AdminEntDetail;
import ks54team01.admin.enterprise.domain.AdminEntList;
import ks54team01.admin.enterprise.service.AdminEntListService;
import ks54team01.customer.member.domain.EntMember;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/enterprise")
public class AdminEntController {
	
	private final AdminEntListService adminEntListService;

	// 입점업체 계약 등록
	@PostMapping("/addContract")
	public String addContract(@ModelAttribute AdminEntAddContract adminEntAddContract, HttpSession session) {
		
		String managerId = (String) session.getAttribute("adminId");
		
		adminEntAddContract.setManagerId(managerId);
		
		adminEntListService.addContract(adminEntAddContract);
		
		return "redirect:/admin/enterprise/List";
	}
	
	@GetMapping("/addContract")
	public String addContract(Model model) {
		List<AdminEntList> entList = adminEntListService.getEntList();
		
		model.addAttribute("title", "입점업체 계약 등록");
		model.addAttribute("entList", entList);
		
		model.addAttribute("defaultFeeRateSales", 0.1);
		model.addAttribute("defaultFeeRateRental", 0.05);
		model.addAttribute("defaultFeeRatePenalty", 0.05);
		model.addAttribute("defaultEntryFee", 3000000);
		model.addAttribute("defaultEntCalDate", 20);
		
		return "admin/enterprise/enterpriseAddContractView";
	}
	
	@GetMapping("/List")
	// 입점업체 목록 조회 + 계약 상태 자동 갱신
	public String getEntList(Model model) {
	    List<AdminEntList> entList = adminEntListService.getEntList();

	    model.addAttribute("title", "입점업체 목록");
	    model.addAttribute("entList", entList);

	    return "admin/enterprise/enterpriseListView";
	}
	
	@GetMapping("/searchEnterprise")
	public String getSearchEnterprise(@RequestParam(name="searchKey", required = false, defaultValue = "ceoCode") String searchKey,
									  @RequestParam(name="searchValue", required = false)String searchValue,
									  Model model){
		List<AdminEntList> entList = adminEntListService.getSearchEnt(searchKey, searchValue);
		
		model.addAttribute("entList", entList);
		model.addAttribute("title", "입점업체목록");
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		
		return "admin/enterprise/enterpriseListView";
	}
	
	// 검색
	@GetMapping("/searchPopupEnt")
	public String searchPopupEnt(@RequestParam(name="searchKey", required = false, defaultValue = "ceoCode") String searchKey,
								@RequestParam(name="searchValue", required = false) String searchValue,
								Model model) {
		List<AdminEntList> searchPopupEntList = adminEntListService.getSearchEnt(searchKey, searchValue);
		model.addAttribute("searchPopupEntList", searchPopupEntList);
		
		return "admin/enterprise/searchPopupEnt";
	}
	
	// 팝업검색 처음 빈페이지
		@GetMapping("/searchPopup")
		public String searchPopup() {
			
			return "admin/enterprise/searchPopupEnt";
		}
	
	// 입점업체 상세 조회
	@GetMapping("/EntDetail")
	 public String entDetail(@RequestParam("ceoCode") String ceoCode, Model model) {
     
		AdminEntDetail detail = adminEntListService.getEntDetail(ceoCode);

		model.addAttribute("entDetail", detail);
        
		return "admin/enterprise/enterpriseDetailView";
    }
	
}
