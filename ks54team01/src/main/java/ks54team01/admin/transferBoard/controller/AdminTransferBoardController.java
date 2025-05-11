package ks54team01.admin.transferBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/transferBoard")
@RequiredArgsConstructor
public class AdminTransferBoardController {

	@GetMapping("/transferBoardDetail")
	public String getTransferBoardDetail(Model model) {
		
		model.addAttribute("title", "양도게시글 상세");
		
	
		return "admin/transferBoard/transferBoardDetailView";
		
	}
	
	
	@GetMapping("/transferBoardList")
	public String getTransferBoardList(Model model) {
		
		model.addAttribute("title", "양도게시글 목록");
		
	
		return "admin/transferBoard/transferBoardListView";
		
	}
	
	
	
	
}
