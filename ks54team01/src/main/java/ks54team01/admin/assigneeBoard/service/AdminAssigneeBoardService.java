package ks54team01.admin.assigneeBoard.service;

import org.springframework.stereotype.Service;

@Service
public interface AdminAssigneeBoardService {

	// 양수 게시글 삭제
	boolean removeAssigneeBoard(String assigneeBoardNum);
}
