package ks54team01.customer.transferBoard.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.customer.transferBoard.domain.CustomerTransferBoard;
import ks54team01.customer.transferBoard.mapper.CustomerTransferBoardMapper;
import ks54team01.customer.transferBoard.service.CustomerTransferBoardService;
import ks54team01.system.util.PageInfo;
import ks54team01.system.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CustomerTransferBoardServiceImpl implements CustomerTransferBoardService{

	// DI 의존성주입
	private final CustomerTransferBoardMapper customerTransferBoardMapper;
	
	/**
	 * 내 양도 게시글 목록 조회
	 */
	@Override
	public List<CustomerTransferBoard> getMyTransferBoardList() {
		List<CustomerTransferBoard> myTransferBoardList = customerTransferBoardMapper.getMyTransferBoardList();
		return myTransferBoardList;
	}
	
	/**
	 * 양도게시글상세조회
	 */
	@Override
	public CustomerTransferBoard getTransferBoardInfoByCode(String transferBoardNum) {
		
		CustomerTransferBoard transferBoardInfo = customerTransferBoardMapper.getTransferBoardInfoByCode(transferBoardNum);
		
		return transferBoardInfo;
	}
	
	/**
	 * 양도게시글목록조회
	 */
	@Override
	public PageInfo<CustomerTransferBoard> getTransferBoardList(Map<String, Object> searchParamMap) {
		
		// 전체 행 개수 조회
		int contentRowCount = customerTransferBoardMapper.getTransferBoardCount(searchParamMap);
		
		List<CustomerTransferBoard> transferBoardList = customerTransferBoardMapper.getTransferBoardList(searchParamMap);
	
		Pageable pageable = (Pageable) searchParamMap.get("pageable");
		
		log.info("contentRowCount: {}", contentRowCount);
		log.info("transferBoardList: {}", transferBoardList);
		
		return new PageInfo<>(transferBoardList, pageable, contentRowCount);
	}
}