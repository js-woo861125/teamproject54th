package ks54team01.admin.transferBoard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ks54team01.admin.transferBoard.service.AdminTransferBoardService;
import ks54team01.common.file.domain.FileMetaData;
import ks54team01.common.file.service.FileService;
import ks54team01.customer.transferBoard.domain.CustomerTransferBoard;
import ks54team01.customer.transferBoard.service.CustomerTransferBoardService;
import ks54team01.system.util.PageInfo;
import ks54team01.system.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin/transferBoard")
@RequiredArgsConstructor
@Slf4j
public class AdminTransferBoardController {

	private final CustomerTransferBoardService customerTransferBoardService;
	private final AdminTransferBoardService adminTransferBoardService;
	private final FileService fileService;
	
	@PostMapping("/removeTransferBoard")
	@ResponseBody
	public boolean removeTransferBoard(@RequestParam(name="transferBoardNum") String transferBoardNum) {
		
		boolean isRemove = adminTransferBoardService.removeTransferBoard(transferBoardNum);
		
		return isRemove;
	}
	
	@GetMapping("/transferBoardDetail")
	public String getTransferBoardDetail(@RequestParam(name = "transferBoardNum", required = false) String transferBoardNum
			   , Model model) {

		CustomerTransferBoard transferBoardInfo = customerTransferBoardService.getTransferBoardInfoByCode(transferBoardNum);
		List<FileMetaData> fileList = fileService.getFileList(transferBoardNum, "extraImage");
		
		model.addAttribute("title", "양도 게시글 상세 조회");
		model.addAttribute("transferBoardInfo", transferBoardInfo);
		model.addAttribute("fileList", fileList);
	
		return "admin/transferBoard/transferBoardDetailView";
	}
	
	
	@GetMapping("/transferBoardList")
	public String getTransferBoardList(@RequestParam(name = "sortValue", required = false) String sortValue
									 , @RequestParam(name = "searchValue", required = false) String searchValue
									 , Pageable pageable
									 , Model model) {

		// 한 페이지에 4 X 4 총 16개 노출
		pageable.setRowPerPage(16);
		
		Map<String, Object> searchParamMap = new HashMap<String, Object>();
		
		if (sortValue != null && !sortValue.equals(""))			searchParamMap.put("sortValue", sortValue);
		
		if (searchValue != null && !searchValue.equals(""))		searchParamMap.put("searchValue", searchValue);
		
		searchParamMap.put("pageable", pageable);
		
		PageInfo<CustomerTransferBoard> transferBoard = customerTransferBoardService.getTransferBoardList(searchParamMap);
		
		var transferBoardList = transferBoard.getContents();
		int currentPage = transferBoard.getCurrentPage();
		int lastPage = transferBoard.getLastPage();
		int startPageNum = transferBoard.getStartPageNum();
		int endPageNum = transferBoard.getEndPageNum();
		int rowPerPage = pageable.getRowPerPage();
		int contentRowCount = transferBoard.getTotalRowCount();
		
		model.addAttribute("title", "양도 게시글 목록");
		model.addAttribute("transferBoardList", transferBoardList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("contentRowCount", contentRowCount);
		
		model.addAttribute("sortValue", sortValue);
		model.addAttribute("searchValue", searchValue);
		
		return "admin/transferBoard/transferBoardListView";
	}
	
	
	
	
}
