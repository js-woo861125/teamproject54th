package ks54team01.customer.transferBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.transferBoard.domain.CustomerTransferBoard;
import ks54team01.system.util.Pageable;

@Mapper
public interface CustomerTransferBoardMapper {
	
	// 양도 게시글 기준 별 정렬 조회
	List<CustomerTransferBoard> getSortTransferBoardList(String sortValue, Pageable pageable);
	
	// 검색시 양도 게시글 총 row 개수 조회
	int getSearchTransferBoardCount();
	
	// 양도 게시글 총 row 개수 조회
	int getTransferBoardCount();
	
	// 양도 게시글 검색 조회
	List<CustomerTransferBoard> getSearchTransferBoard(String searchKey, String searchValue, Pageable pageable);
	
	// 양도게시글상세조회
	CustomerTransferBoard getTransferBoardInfoByCode(String transferBoardNum);
	
	// 양도 게시글 목록 조회
	List<CustomerTransferBoard> getTransferBoardList(Pageable pageable);
	
}