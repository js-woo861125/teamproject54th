package ks54team01.customer.transferBoard.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.transferBoard.domain.CustomerTransferBoard;

@Mapper
public interface CustomerTransferBoardMapper {
	
	
	// 양도 게시글 총 row 개수 조회
	int getTransferBoardCount(Map<String, Object> searchParamMap);
	
	// 양도게시글상세조회
	CustomerTransferBoard getTransferBoardInfoByCode(String transferBoardNum);
	
	// 양도 게시글 목록 조회
	List<CustomerTransferBoard> getTransferBoardList(Map<String, Object> searchParamMap);
	
}