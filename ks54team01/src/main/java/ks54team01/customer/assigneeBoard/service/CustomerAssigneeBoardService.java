package ks54team01.customer.assigneeBoard.service;

import java.util.List;
import java.util.Map;

import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.customer.assigneeBoard.domain.CustomerAssigneeBoard;
import ks54team01.system.util.PageInfo;

public interface CustomerAssigneeBoardService {

	// 양수 게시글 삭제
	boolean removeMyAssigneeBoard(String assigneeBoardNum);
	
	// 양수 게시글 수정
	void modifyAssigneeBoard(CustomerAssigneeBoard customerAssigneeBoard);
	
	// 양수 게시글 등록
	void addAssigneeBoard(CustomerAssigneeBoard customerAssigneeBoard, String customerId);
	
	// 양수 게시글 등록 시 카테고리 선택
	List<CustomerAssigneeBoard> selectAssigneeBoardProduct(String productCategoryNum);
	
	// 양수 게시글 등록 시 카테고리 선택
	List<CustomerAssigneeBoard> selectAssigneeBoardCategory(String mdCategoryNo);
	
	// 양수 게시글 등록 
	List<ProductInfoCategory> addAssigneeBoard();
	
	// 내 양수 게시글 목록 조회
	List<CustomerAssigneeBoard> getMyAssigneeBoardList(String customerId);
	
	// 양수 게시글 정보 조회
	CustomerAssigneeBoard getAssigneeBoardInfoByCode(String assigneeBoardNum);
	
	// 양수 게시글 목록 조회
	PageInfo<CustomerAssigneeBoard> getAssigneeBoardList(Map<String, Object> searchParamMap);
}
