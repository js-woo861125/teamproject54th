package ks54team01.customer.transferBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks54team01.customer.transferBoard.domain.CustomerTransferBoard;
import ks54team01.customer.transferBoard.service.CustomerTransferBoardService;
import ks54team01.system.util.PageInfo;
import ks54team01.system.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/transferBoard")
@Slf4j
public class CustomerTransferBoardController {

	private final CustomerTransferBoardService customerTransferBoardService;
	
	/*
	 * @GetMapping("/sortTransferBoardList") public String
	 * getSortTransferBoard(@RequestParam(name="sortValue", required = false) String
	 * sortValue , Pageable pageable , Model model) {
	 * 
	 * log.info("sortValue: {}", sortValue);
	 * 
	 * PageInfo<CustomerTransferBoard> transferBoard =
	 * customerTransferBoardService.getSortTransferBoardList(sortValue, pageable);
	 * 
	 * var transferBoardList = transferBoard.getContents(); int currentPage =
	 * transferBoard.getCurrentPage(); int lastPage = transferBoard.getLastPage();
	 * int startPageNum = transferBoard.getStartPageNum(); int endPageNum =
	 * transferBoard.getEndPageNum(); int rowPerPage = pageable.getRowPerPage(); int
	 * contentRowCount = transferBoard.getTotalRowCount();
	 * 
	 * model.addAttribute("titel", "양도게시글목록");
	 * model.addAttribute("transferBoardList", transferBoardList);
	 * model.addAttribute("currentPage", currentPage);
	 * model.addAttribute("lastPage", lastPage); model.addAttribute("startPageNum",
	 * startPageNum); model.addAttribute("endPageNum", endPageNum);
	 * model.addAttribute("rowPerPage", rowPerPage);
	 * 
	 * model.addAttribute("sortValue", sortValue);
	 * model.addAttribute("contentRowCount", contentRowCount);
	 * 
	 * return "customer/transferBoard/transferBoardListView"; }
	 * 
	 * @GetMapping("/searchTransferBoard") public String
	 * getSearchTransferBoard(@RequestParam(name="searchValue", required = false)
	 * String searchValue , Model model , Pageable pageable) {
	 * 
	 * log.info("searchValue: {}", searchValue); log.info("pageable: {}", pageable);
	 * 
	 * PageInfo<CustomerTransferBoard> transferBoard =
	 * customerTransferBoardService.getSearchTransferBoard(searchValue, pageable);
	 * 
	 * var transferBoardList = transferBoard.getContents(); int currentPage =
	 * transferBoard.getCurrentPage(); int lastPage = transferBoard.getLastPage();
	 * int startPageNum = transferBoard.getStartPageNum(); int endPageNum =
	 * transferBoard.getEndPageNum(); int rowPerPage = pageable.getRowPerPage(); int
	 * contentRowCount = transferBoard.getTotalRowCount();
	 * 
	 * model.addAttribute("titel", "양도게시글목록");
	 * model.addAttribute("transferBoardList", transferBoardList);
	 * model.addAttribute("currentPage", currentPage);
	 * model.addAttribute("lastPage", lastPage); model.addAttribute("startPageNum",
	 * startPageNum); model.addAttribute("endPageNum", endPageNum);
	 * model.addAttribute("rowPerPage", rowPerPage);
	 * 
	 * model.addAttribute("searchValue", searchValue);
	 * model.addAttribute("contentRowCount", contentRowCount);
	 * 
	 * return "customer/transferBoard/transferBoardListView"; }
	 */
	
	@GetMapping("/transferBoardDetail")
	public String getTransferBoardDetail(@RequestParam(name="transferBoardNum", required = false) String transferBoardNum
										, Model model) {
		
		log.info("게시글조회 코드: {}", transferBoardNum);
		
		CustomerTransferBoard transferBoardInfo = customerTransferBoardService.getTransferBoardInfoByCode(transferBoardNum);

		
		model.addAttribute("title", "양도 게시글 상세 조회");
		model.addAttribute("transferBoardInfo", transferBoardInfo);
		
		return "customer/transferBoard/transferBoardDetailView";
	}
	
	
	@GetMapping("/transferBoardList")
	public String getTransferBoardList(@RequestParam(name="sortValue", required = false) String sortValue
									  , @RequestParam(name="searchValue", required = false) String searchValue
									  , Pageable pageable, Model model) {
		
		log.info("sortValue: {}", sortValue);
		log.info("searchValue: {}", searchValue);
		
		
		PageInfo<CustomerTransferBoard> transferBoard;

		if(sortValue != null && !sortValue.equals("")) {
			transferBoard = customerTransferBoardService.getSortTransferBoardList(sortValue, pageable);
		}
		else if(searchValue != null && !searchValue.equals("")) {
			transferBoard = customerTransferBoardService.getSearchTransferBoard(searchValue, pageable);
		}
		else {
			transferBoard = customerTransferBoardService.getTransferBoardList(pageable);
		}
		
		var transferBoardList = transferBoard.getContents();
		int currentPage = transferBoard.getCurrentPage();
		int lastPage = transferBoard.getLastPage();
		int startPageNum = transferBoard.getStartPageNum();
		int endPageNum = transferBoard.getEndPageNum();
		int rowPerPage = pageable.getRowPerPage();
		int contentRowCount = transferBoard.getTotalRowCount();	
		
		
		model.addAttribute("titel", "양도 게시글 목록");
		model.addAttribute("transferBoardList", transferBoardList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("contentRowCount", contentRowCount);
		
		model.addAttribute("sortValue", sortValue);
		model.addAttribute("searchValue", searchValue);
		
		
		return "customer/transferBoard/transferBoardListView";
	}
	
}