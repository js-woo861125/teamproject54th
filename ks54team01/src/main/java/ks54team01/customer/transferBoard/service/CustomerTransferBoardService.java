package ks54team01.customer.transferBoard.service;

import java.util.List;

import ks54team01.customer.transferBoard.domain.CustomerTransferBoard;

public interface CustomerTransferBoardService {

	// 양도 게시글 검색 조회
	List<CustomerTransferBoard> getSearchTransferBoard(String searchKey, String searchValue);
	
	// 회원 정보 조회
	CustomerTransferBoard getTransferBoardInfoByCode(String transferBoardNum);
	
	// 회원 목록 조회
	List<CustomerTransferBoard> getTransferBoardList();
}