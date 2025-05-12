package ks54team01.customer.transferBoard.service.impl;

import java.util.List;

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
	 * 양도게시글정렬조회
	 */
	@Override
	public PageInfo<CustomerTransferBoard> getSortTransferBoardList(String sortValue, Pageable pageable) {
		
		// 마지막 페이지를 구하기 위해 전체 행의 개수 조회
		int contentRowCount = customerTransferBoardMapper.getTransferBoardCount();
		
		
		
		List<CustomerTransferBoard> transferBoardList = customerTransferBoardMapper.getSortTransferBoardList(sortValue, pageable);
		
		return new PageInfo<>(transferBoardList, pageable, contentRowCount);
	}
	
	/**
	 * 양도게시글검색조회
	 */
	@Override
	public PageInfo<CustomerTransferBoard> getSearchTransferBoard(String searchValue, Pageable pageable) {

		// 마지막 페이지를 구하기 위해 전체 행의 개수 조회
		int contentRowCount = customerTransferBoardMapper.getSearchTransferBoardCount();
		
		List<CustomerTransferBoard> transferBoardList = customerTransferBoardMapper.getSearchTransferBoard(searchValue, pageable);
		
		return new PageInfo<>(transferBoardList, pageable, contentRowCount);
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
	public PageInfo<CustomerTransferBoard> getTransferBoardList(Pageable pageable) {
		// 마지막 페이지를 구하기 위해 전체 행의 개수 조회
		int contentRowCount = customerTransferBoardMapper.getTransferBoardCount();
		
		List<CustomerTransferBoard> transferBoardList = customerTransferBoardMapper.getTransferBoardList(pageable);
		
		log.info("contentRowCount: {}", contentRowCount);
		log.info("transferBoardList: {}", transferBoardList);
		
		return new PageInfo<>(transferBoardList, pageable, contentRowCount);
	}
}