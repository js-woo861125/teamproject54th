package ks54team01.customer.transferBoard.service;


import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import ks54team01.customer.transferBoard.domain.CustomerTransferBoard;
import ks54team01.system.util.PageInfo;

public interface CustomerTransferBoardService {
	
	// 양도 신청
	void applyTranfer(CustomerTransferBoard customerTransferBoard, String customerId);
	
	// 양도 게시글 삭제
	boolean removeMyTransferBoard(String transferBoardNum);
	
	// 양도 게시글 수정
	void modifyTransferBoard(CustomerTransferBoard customerTransferBoard, MultipartFile[] mainImage, MultipartFile[] extraImage);
	
	// 양도 게시글 등록
	void addTransferBoard(CustomerTransferBoard customerTransferBoard, MultipartFile[] mainImage, MultipartFile[] extraImage);
	
	// 양도 게시글 등록 시 계약 정보
	CustomerTransferBoard getMyContractInfo(String rentalContractNum);
	
	// 양도 게시글 등록 버튼 클릭 시 유효한 렌탈 목록 체크
	List<CustomerTransferBoard> getMyContractListByCustomerId(String customerId);
	
	// 내 양도 게시글 목록 조회
	List<CustomerTransferBoard> getMyTransferBoardList(String customerId);
	
	// 양도 게시글 정보 조회
	CustomerTransferBoard getTransferBoardInfoByCode(String transferBoardNum);
	
	// 양도 게시글 목록 조회
	PageInfo<CustomerTransferBoard> getTransferBoardList(Map<String, Object> searchParamMap);
}