package ks54team01.customer.assigneeBoard.service;

import ks54team01.customer.assigneeBoard.domain.CustomerAssigneeBoard;
import ks54team01.system.util.PageInfo;
import ks54team01.system.util.Pageable;

public interface CustomerAssigneeBoardService {

	// 양수 게시글 정보 조회
	CustomerAssigneeBoard getAssigneeBoardInfoByCode(String assigneeBoardNum);
	
	// 양수 게시글 목록 조회
	PageInfo<CustomerAssigneeBoard> getAssigneeBoardList(Pageable pageable);
}
