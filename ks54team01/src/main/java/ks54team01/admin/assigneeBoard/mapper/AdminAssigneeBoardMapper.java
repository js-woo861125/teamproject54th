package ks54team01.admin.assigneeBoard.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminAssigneeBoardMapper {

	// 양수 게시글 삭제
	int removeAssigneeBoard(String assigneeBoardNum);
}
