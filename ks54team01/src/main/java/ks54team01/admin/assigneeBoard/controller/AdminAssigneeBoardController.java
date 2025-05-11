package ks54team01.admin.assigneeBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/assigneeBoard")
@RequiredArgsConstructor
public class AdminAssigneeBoardController {
	
	
	@GetMapping("/assigneeBoardDetail")
	public String getAssigneeBoardDetail(Model model) {
		
		model.addAttribute("title", "양수게시글 상세");
		
		return "admin/assigneeBoard/assigneeBoardDetailView";
	}

	
	@GetMapping("/assigneeBoardList")
	public String getAssigneeBoardList(Model model) {
		
		model.addAttribute("title", "양수게시글 목록");
		
		return "admin/assigneeBoard/assigneeBoardListView";
				
	}
}
