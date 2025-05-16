package ks54team01.customer.assigneeBoard.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks54team01.customer.assigneeBoard.domain.CustomerAssigneeBoard;

@Mapper
public interface CustomerAssigneeBoardMapper {

	// 내 양수 게시글 목록 조회
	List<CustomerAssigneeBoard> getMyAssigneeBoardList();
	
	// 양수 게시글 전체 행 수 조회
	int getAssigneeBoardCount(Map<String, Object> searchParamMap);

	// 양수게시글상세조회
	CustomerAssigneeBoard getAssigneeBoardInfoByCode(String assigneeBoardNum);
	
	
	// 양수 게시글 목록 조회
	List<CustomerAssigneeBoard> getAssigneeBoardList(Map<String, Object> searchParamMap);
}
