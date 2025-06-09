package ks54team01.admin.assigneeBoard.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.admin.assigneeBoard.mapper.AdminAssigneeBoardMapper;
import ks54team01.admin.assigneeBoard.service.AdminAssigneeBoardService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminAssigneeBoardServiceImpl implements AdminAssigneeBoardService {

	private final AdminAssigneeBoardMapper adminAssigneeBoardMapper;
	
	@Override
	public boolean removeAssigneeBoard(String assigneeBoardNum) {

		int deleted = adminAssigneeBoardMapper.removeAssigneeBoard(assigneeBoardNum);
		
		boolean isDel = deleted > 0 ? true : false;
		
		return isDel;
		
	}
}
