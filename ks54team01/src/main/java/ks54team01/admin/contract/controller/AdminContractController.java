package ks54team01.admin.contract.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks54team01.admin.contract.domain.AdminContract;
import ks54team01.admin.contract.service.AdminContractService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/contract")
public class AdminContractController {

	private final AdminContractService adminContractService;
	
	
	// 검색
	@GetMapping("/searchContractList")
	public String getSearchContractList(@RequestParam(name = "searchKey", required = false) String searchKey, 
										@RequestParam(name = "searchValue", required = false) String searchValue, 
										Model model) {
		


		List<AdminContract> contractList;

		

		model.addAttribute("title", "입점업체&고객 계약목록");
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);

		
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("searchKey", searchKey);
		paramMap.put("searchValue", searchValue);
		
		contractList = adminContractService.getSearchContractList(paramMap);
		
		String firstEntBrno = null;
		String firstEntName = null;
		String firstEntCode = null;

		if (contractList != null && !contractList.isEmpty()) {
			// 리스트의 첫 번째 AdminContract 객체를 가져옵니다.
			AdminContract firstContract = contractList.get(0);

			// 해당 객체에서 사업자번호와 상호 값을 가져옵니다.
			firstEntCode = firstContract.getEntCeoNo();
			firstEntBrno = firstContract.getEntBrno();
			firstEntName = firstContract.getEntName();

			System.out.println("추출된 첫 번째 계약의 사업자번호 (entBrno): " + firstEntBrno);
			System.out.println("추출된 첫 번째 계약의 상호 (entName): " + firstEntName);

			// 추출된 단일 값들을 뷰(Thymeleaf 템플릿)로 전달합니다.
			model.addAttribute("onlyOneEntBrno", firstEntBrno);
			model.addAttribute("onlyOneEntName", firstEntName);
			model.addAttribute("onlyOneEntNo", firstEntCode);
			
		} else {
			System.out.println("검색된 계약 목록이 비어 있습니다. 특정 값을 가져올 수 없습니다.");
			// 리스트가 비었을 때 뷰에 전달할 기본 값이나 메시지를 설정할 수 있습니다.
			model.addAttribute("onlyOneEntBrno", "데이터 없음");
			model.addAttribute("onlyOneEntName", "데이터 없음");
		}

		// 뷰(Thymeleaf 템플릿)로 전달할 공통 데이터 설정
		model.addAttribute("title", "입점업체&고객 계약목록"); // 페이지 제목
		model.addAttribute("ContractList", contractList);     // 조회된 전체 계약 목록
		model.addAttribute("searchKey", searchKey);         // 사용자가 선택한 검색 기준
		model.addAttribute("searchValue", searchValue);       // 사용자가 입력한 검색어

		// 렌더링할 뷰의 논리적 이름 반환
		return "admin/contract/contractListView";
	}
	
	// 조회
	@GetMapping("/contractList")
	public String getContractList(Model model) {
		
		List<AdminContract> contractList = adminContractService.getContractList();
		
		model.addAttribute("title", "입점업체&고객 계약목록");
		model.addAttribute("contractList", contractList);
		
		return "admin/contract/contractListView";
	}
	
	
	// 조회한 업체정보
	
	
	
	//렌탈계약목록 처음 빈페이지
	@GetMapping("/contractListView")
	public String getContractList() {
		
	
		
		return "admin/contract/contractListView";
	}
	
}
