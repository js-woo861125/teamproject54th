package ks54team01.customer.assigneeBoard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks54team01.customer.assigneeBoard.domain.CustomerAssigneeBoard;
import ks54team01.customer.assigneeBoard.service.CustomerAssigneeBoardService;
import ks54team01.system.util.PageInfo;
import ks54team01.system.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/assigneeBoard")
@Slf4j
public class CustomerAssigneeBoardController {

	private final CustomerAssigneeBoardService customerAssigneeBoardService;
	
	@GetMapping("/addAssigneeBoard")
	public String addAssigneeBoard() {
		
		return "customer/assigneeBoard/addAssigneeBoardView";
	}
	
	@GetMapping("/myAssigneeBoardList")
	public String getMyAssigneeBoardList(Model model) {
		
		List<CustomerAssigneeBoard> myAssigneeBoardList = customerAssigneeBoardService.getMyAssigneeBoardList();
		
		model.addAttribute("title", "내 양수 게시글 목록 조회");
		model.addAttribute("myAssigneeBoardList", myAssigneeBoardList);
		return "customer/myPage/myAssigneeBoardListView";
	}
	
	@GetMapping("/assigneeBoardDetail")
	public String getAssigneeBoardDetail(@RequestParam(name="assigneeBoardNum", required = false) String assigneeBoardNum
										, Model model) {
		
		log.info("게시글조회 코드: {}", assigneeBoardNum);
		
		CustomerAssigneeBoard assigneeBoardInfo = customerAssigneeBoardService.getAssigneeBoardInfoByCode(assigneeBoardNum);
		
		model.addAttribute("title", "양수 게시글 상세 조회");
		model.addAttribute("assigneeBoardInfo", assigneeBoardInfo);
		
		return "customer/assigneeBoard/assigneeBoardDetailView";
	}
	
	@GetMapping("/assigneeBoardList")
	public String getAssigneeBoardList(@RequestParam(name="sortValue", required = false) String sortValue
			  						 , @RequestParam(name="searchValue", required = false) String searchValue
			  						 , Pageable pageable, Model model) {
		
		// 한 페이지에 총 10개 노출
		pageable.setRowPerPage(10);
		
		Map<String, Object> searchParamMap  = new HashMap<String, Object>();
		
		if(sortValue != null && !sortValue.equals("")) 		searchParamMap.put("sortValue", sortValue);
		
		if(searchValue != null && !searchValue.equals("")) 	searchParamMap.put("searchValue", searchValue);
		
		searchParamMap.put("pageable", pageable);
		
		PageInfo<CustomerAssigneeBoard> assigneeBoard = customerAssigneeBoardService.getAssigneeBoardList(searchParamMap);
		
		var assigneeBoardList = assigneeBoard.getContents();
		int currentPage = assigneeBoard.getCurrentPage();
		int lastPage = assigneeBoard.getLastPage();
		int startPageNum = assigneeBoard.getStartPageNum();
		int endPageNum = assigneeBoard.getEndPageNum();
		int rowPerPage = pageable.getRowPerPage();
		int contentRowCount = assigneeBoard.getTotalRowCount();	
		
		
		model.addAttribute("title", "양수게시글목록");
		model.addAttribute("assigneeBoardList", assigneeBoardList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("contentRowCount", contentRowCount);
		
		model.addAttribute("sortValue", sortValue);
		model.addAttribute("searchValue", searchValue);
		
		return "customer/assigneeBoard/assigneeBoardListView";
	}
}
