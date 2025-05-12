package ks54team01.customer.transferBoard.service;


import java.util.Map;

import ks54team01.customer.transferBoard.domain.CustomerTransferBoard;
import ks54team01.system.util.PageInfo;

public interface CustomerTransferBoardService {

	
	// 양도 게시글 정보 조회
	CustomerTransferBoard getTransferBoardInfoByCode(String transferBoardNum);
	
	// 양도 게시글 목록 조회
	PageInfo<CustomerTransferBoard> getTransferBoardList(Map<String, Object> searchParamMap);
}