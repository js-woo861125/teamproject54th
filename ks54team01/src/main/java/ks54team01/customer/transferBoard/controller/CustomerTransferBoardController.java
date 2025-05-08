package ks54team01.customer.transferBoard.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks54team01.customer.transferBoard.domain.CustomerTransferBoard;
import ks54team01.customer.transferBoard.service.CustomerTransferBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/transferBoard")
@Slf4j
public class CustomerTransferBoardController {

	private final CustomerTransferBoardService customerTransferBoardService;
	
	@GetMapping("/transferBoardDetail")
	public String getTransferBoardDetail(@RequestParam(name="transferBoardNum", required = false) String transferBoardNum,
								Model model) {
		
		log.info("게시글조회 코드: {}", transferBoardNum);
		
		CustomerTransferBoard transferBoardInfo = customerTransferBoardService.getTransferBoardInfoByCode(transferBoardNum);

		
		model.addAttribute("title", "양도 게시글 상세 조회");
		model.addAttribute("transferBoardInfo", transferBoardInfo);
		
		return "/customer/transferBoard/transferBoardDetailView";
	}
	
	
	@GetMapping("/transferBoardList")
	public String getTransferBoardList(Model model) {
		
		List<CustomerTransferBoard> transferBoardList = customerTransferBoardService.getTransferBoardList();
		
		model.addAttribute("titel", "양도게시글목록");
		model.addAttribute("transferBoardList", transferBoardList);
		return "/customer/transferBoard/transferBoardListView";
	}
	
}