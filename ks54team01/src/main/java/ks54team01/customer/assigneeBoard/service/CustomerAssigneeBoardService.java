package ks54team01.customer.assigneeBoard.service;

import java.util.List;
import java.util.Map;

import ks54team01.customer.assigneeBoard.domain.CustomerAssigneeBoard;
import ks54team01.system.util.PageInfo;

public interface CustomerAssigneeBoardService {

	// 내 양수 게시글 목록 조회
	List<CustomerAssigneeBoard> getMyAssigneeBoardList();
	
	// 양수 게시글 정보 조회
	CustomerAssigneeBoard getAssigneeBoardInfoByCode(String assigneeBoardNum);
	
	// 양수 게시글 목록 조회
	PageInfo<CustomerAssigneeBoard> getAssigneeBoardList(Map<String, Object> searchParamMap);
}
