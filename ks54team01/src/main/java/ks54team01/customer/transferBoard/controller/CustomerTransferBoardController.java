package ks54team01.customer.transferBoard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import ks54team01.common.file.domain.FileMetaData;
import ks54team01.common.file.service.FileService;
import ks54team01.common.file.util.FilesUtils;
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

	@Value("${file.path}")
	private String fileRealPath;
	
	@Autowired
	private final FilesUtils filesUtils;
	
	private final CustomerTransferBoardService customerTransferBoardService;
	private final FileService fileService;

	@PostMapping("/applyTransfer")
	public String applyTransfer(CustomerTransferBoard customerTransferBoard
							  , HttpSession session) {
		
		String customerId = (String) session.getAttribute("loginId");
		
		log.info("customerId: {}", customerId);
		log.info("customerTransferBoard : {}", customerTransferBoard);
		
		customerTransferBoard.setCustomerId(customerId);
		
		customerTransferBoardService.applyTranfer(customerTransferBoard);
		
		
		return "redirect:/customer/transferBoard/transferBoardList";
	}
	
	@PostMapping("/removeMyTransferBoard")
	@ResponseBody
	public boolean removeMyTransferBoard(@RequestParam(name="transferBoardNum") String transferBoard) {
		
		boolean isRemove = customerTransferBoardService.removeMyTransferBoard(transferBoard);
		
		return isRemove;
	}
	
	@GetMapping("/deleteFiles")
	public String deleteFiles(@RequestParam(name="filesIdx") List<String> filesIdx) {
		for(String deleteFile : filesIdx){
			fileService.deleteFileByIdx(deleteFile);
			log.info("deleteFile: {}", deleteFile);
		}
		
		return "redirect:/customer/transferBoard/modifyTransferBoard";
	}
	
	@GetMapping("/deleteFile")
	public String deleteFile(String fileIdx) {
		
		fileService.deleteFileByIdx(fileIdx);
		
		return "redirect:/customer/transferBoard/modifyTransferBoard";
	}
	
	@PostMapping("/modifyTransferBoard")
	public String modifyTransferBoard(CustomerTransferBoard customerTransferBoard
									, @RequestPart(name="mainImage") MultipartFile[] mainImage
									, @RequestPart(name="extraImage") MultipartFile[] extraImage
									, @RequestParam(name="deleteFile", required = false) List<String> deleteFile
									, @RequestParam(name="deleteMainImage", required = false) List<String> deleteMainImage) {
		
		log.info("customerTrnasferBoard:{}", customerTransferBoard);
		
		log.info("mainImage:{}", mainImage[0].isEmpty());
		log.info("extraImage:{}", extraImage[0].isEmpty());
		log.info("deleteFile:{}", deleteFile != null);
		log.info("deleteMainImage:{}", deleteMainImage != null);
		customerTransferBoardService.modifyTransferBoard(customerTransferBoard, mainImage, extraImage, deleteFile, deleteMainImage);
		return "redirect:/customer/transferBoard/myTransferBoardList";
	}
	
	@GetMapping("/modifyTransferBoard")
	public String modifyTransferBoard(@RequestParam(name="transferBoardNum") String transferBoardNum,
									  Model model) {
		
		log.info("게시글 수정 코드: {}", transferBoardNum);
		
		CustomerTransferBoard customerTransferBoardInfo = customerTransferBoardService.getTransferBoardInfoByCode(transferBoardNum);
		List<FileMetaData> mainImage = fileService.getFileList(transferBoardNum, "mainImage");
		List<FileMetaData> fileList = fileService.getFileList(transferBoardNum, "extraImage");
		
		model.addAttribute("title", "양도 게시글 수정");
		model.addAttribute("customerTransferBoardInfo", customerTransferBoardInfo);
		model.addAttribute("mainImage", mainImage);
		model.addAttribute("fileList", fileList);
		
		
		return "customer/transferBoard/modifyTransferBoardView";
	}
	
	@PostMapping("/addTransferBoard") 
	public String addTransferBoard(CustomerTransferBoard customerTransferBoard
								  , @RequestParam("mainImage") MultipartFile[] mainImage
								  , @RequestParam("extraImage") MultipartFile[] extraImage
								  , HttpSession session) {
		
		String customerId = (String) session.getAttribute("loginId");
		
		log.info("customerId: {}", customerId);
		
		customerTransferBoard.setCustomerId(customerId);
		
		customerTransferBoardService.addTransferBoard(customerTransferBoard, mainImage, extraImage);
		
		return "redirect:/customer/transferBoard/transferBoardList"; 
	}
	

	@PostMapping("/deleteImage")
	@ResponseBody
	public ResponseEntity<?> deleteImage(@RequestParam("imageUrl") String imagePath){
		
		if(imagePath == null || imagePath.isBlank()) {
			return ResponseEntity.badRequest().body("잘못된 이미지 경로");
		}
		boolean isDelete = filesUtils.deleteFileByPath(imagePath);
		
		if(isDelete) return ResponseEntity.ok("성공");
		
		return ResponseEntity.ok("실패");
	}
	
	@PostMapping("/uploadImage")
	@ResponseBody
	public Map<String, Object> uploadImage(@RequestParam("upload") MultipartFile multipartFile){
		
		Map<String, Object> response = new HashMap<String, Object>(); 
		FileMetaData fileInfo = filesUtils.uploadFile(multipartFile);
		
		if(fileInfo != null) {	
			response.put("url", fileInfo.getFilePath());
			response.put("uploaded", "1");
			response.put("fileName", fileInfo.getFileOriginalName());
		}else {
			Map<String, Object> error = new HashMap<String, Object>();
			error.put("message", "파일이미지 업로드 실패");
			response.put("uploaded", "0");
			response.put("error", error);
		}
		
		return response;
	}
	
	@PostMapping("/requestRentalInfo")
	public String requestRentalInfo(@RequestParam(name="rentalContractNum")  String rentalContractNum,
			 						HttpSession session) {
		
		/*
		 * String customerId = (String) session.getAttribute("loginId");
		 * 
		 * log.info("customerId: {}", customerId);
		 */
		
	    // 계약 정보 가져오기
	    CustomerTransferBoard myRentalInfo = customerTransferBoardService.getMyContractInfo(rentalContractNum);

//	    myRentalInfo.setCustomerId(customerId);
	    
	    session.setAttribute("myRentalInfo", myRentalInfo);
	    // 등록 페이지로 이동
	    return "redirect:/customer/transferBoard/addTransferBoard";
	}

	
	@PostMapping("/getMyContracts")
	@ResponseBody
	public List<CustomerTransferBoard> getMyContracts(HttpSession session) {
	    
		String customerId = (String) session.getAttribute("loginId");
	    
		List<CustomerTransferBoard> myRentalList = customerTransferBoardService.getMyContractListByCustomerId(customerId);
		
	    return myRentalList;
	}
	
	@GetMapping("/addTransferBoard")
	public String addTransferBoard(@SessionAttribute("myRentalInfo") CustomerTransferBoard myRentalInfo
								 , Model model
								 , HttpSession session) {
	    
		String customerId = (String) session.getAttribute("loginId");
		
		log.info("customerId: {}", customerId);
		
	    model.addAttribute("title", "양도 게시글 등록 페이지");
	    model.addAttribute("myRentalInfo", myRentalInfo);
	    
	    return "customer/transferBoard/addTransferBoardView";
	}

	@GetMapping("/myTransferBoardList")
	public String getMyTransferBoardList(Model model
									   , HttpSession session) {

		String customerId = (String) session.getAttribute("loginId");
		
		log.info("customerId: {}", customerId);
		
		List<CustomerTransferBoard> myTransferBoardList = customerTransferBoardService.getMyTransferBoardList(customerId);
		model.addAttribute("title", "내 양도 게시글 목록 조회");
		model.addAttribute("myTransferBoardList", myTransferBoardList);
		return "customer/myPage/myTransferBoardListView";
	}

	@GetMapping("/transferBoardDetail")
	public String getTransferBoardDetail(@RequestParam(name = "transferBoardNum", required = false) String transferBoardNum
									   , Model model) {

		CustomerTransferBoard transferBoardInfo = customerTransferBoardService.getTransferBoardInfoByCode(transferBoardNum);
		List<FileMetaData> fileList = fileService.getFileList(transferBoardNum, "extraImage");
		
		model.addAttribute("title", "양도 게시글 상세 조회");
		model.addAttribute("transferBoardInfo", transferBoardInfo);
		model.addAttribute("fileList", fileList);
		
		log.info("transferBoardInfo:{}", transferBoardInfo);

		return "customer/transferBoard/transferBoardDetailView";
	}

	@GetMapping("/transferBoardList")
	public String getTransferBoardList(@RequestParam(name = "sortValue", required = false) String sortValue
									 , @RequestParam(name = "searchValue", required = false) String searchValue
									 , Pageable pageable
									 , Model model) {

		// 한 페이지에 4 X 4 총 16개 노출
		pageable.setRowPerPage(16);

		Map<String, Object> searchParamMap = new HashMap<String, Object>();

		if (sortValue != null && !sortValue.equals(""))
			searchParamMap.put("sortValue", sortValue);

		if (searchValue != null && !searchValue.equals(""))
			searchParamMap.put("searchValue", searchValue);

		searchParamMap.put("pageable", pageable);

		PageInfo<CustomerTransferBoard> transferBoard = customerTransferBoardService
				.getTransferBoardList(searchParamMap);

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

		return "customer/transferBoard/transferBoardListView";
	}

}