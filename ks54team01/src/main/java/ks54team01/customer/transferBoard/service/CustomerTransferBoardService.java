package ks54team01.customer.transferBoard.service;


import ks54team01.customer.transferBoard.domain.CustomerTransferBoard;
import ks54team01.system.util.PageInfo;
import ks54team01.system.util.Pageable;

public interface CustomerTransferBoardService {

	// 양도 게시글 정렬 조회
	PageInfo<CustomerTransferBoard> getSortTransferBoardList(String sortValue, Pageable pageable);
	
	// 양도 게시글 검색 조회
	PageInfo<CustomerTransferBoard> getSearchTransferBoard(String searchKey, String searchValue, Pageable pageable);
	
	// 양도 게시글 정보 조회
	CustomerTransferBoard getTransferBoardInfoByCode(String transferBoardNum);
	
	// 양도 게시글 목록 조회
	PageInfo<CustomerTransferBoard> getTransferBoardList(Pageable pageable);
}