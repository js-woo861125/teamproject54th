package ks54team01.customer.transferBoard.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ks54team01.common.file.service.FileService;
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
	private final FileService fileService;
	
	/**
	 * 양도 게시글 수정
	 */
	@Override
	public void modifyTransferBoard(CustomerTransferBoard customerTransferBoard) {
		
		customerTransferBoardMapper.modifyTransferBoard(customerTransferBoard);
	}
	
	/**
	 * 양도 게시글 등록
	 */
	@Override
	public void addTransferBoard(CustomerTransferBoard customerTransferBoard
								, MultipartFile[] mainImage, MultipartFile[] extraImage) {
		
		String transferBoardNum =  "transfer_board_" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
		customerTransferBoard.setTransferBoardNum(transferBoardNum);	
		customerTransferBoardMapper.addTransferBoard(customerTransferBoard);
	
		//  파일 메타데이터 DB에 등록   
        fileService.addFiles(mainImage, "mainImage", customerTransferBoard.getTransferBoardNum());
        fileService.addFiles(extraImage, "extraImage", customerTransferBoard.getTransferBoardNum());
	}
	
	@Override
	public CustomerTransferBoard getMyContractInfo(String rentalContractNum) {
		
		CustomerTransferBoard myRentalInfo = customerTransferBoardMapper.getMyContractInfo(rentalContractNum);
		
		return myRentalInfo;
	}
	
	/**
	 * 양도 게시글 등록 버튼 클릭 시 유효한 렌탈 목록 체크
	 */
	@Override
	public List<CustomerTransferBoard> getMyContractListByCustomerId(String customerId) {

		List<CustomerTransferBoard> myRentalList =  customerTransferBoardMapper.getMyContractListByCustomerId(customerId);
		
		return myRentalList;
	}
	
	
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