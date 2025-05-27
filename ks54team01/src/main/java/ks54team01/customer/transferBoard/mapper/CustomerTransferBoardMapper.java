package ks54team01.customer.transferBoard.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.transferBoard.domain.CustomerTransferBoard;

@Mapper
public interface CustomerTransferBoardMapper {
	
	// 양도 신청
	int applyTransfer(CustomerTransferBoard customerTransferBoard);
	
	// 양도 게시글 삭제
	int removeMyTransferBoard(String transferBoardNum);
	
	// 양도 게시글 수정
	int modifyTransferBoard(CustomerTransferBoard customerTransferBoard);
	
	// 양도 게시글 등록
	int addTransferBoard(CustomerTransferBoard customerTransferBoard);
	
	// 게시글 등록 시 렌탈계약 정보 조회
	CustomerTransferBoard getMyContractInfo(String rentalContractNum);
	
	// 양도 게시글 등록 버튼 클릭 시 유효한 렌탈 목록 체크
	List<CustomerTransferBoard> getMyContractListByCustomerId(String customerId);
	
	// 내 양도 게시글 목록 조회
	List<CustomerTransferBoard> getMyTransferBoardList();
	
	// 양도 게시글 전체 행 수 조회
	int getTransferBoardCount(Map<String, Object> searchParamMap);
	
	// 양도게시글상세조회
	CustomerTransferBoard getTransferBoardInfoByCode(String transferBoardNum);
	
	// 양도 게시글 목록 조회
	List<CustomerTransferBoard> getTransferBoardList(Map<String, Object> searchParamMap);
	
}