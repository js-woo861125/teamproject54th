package ks54team01.customer.assigneeBoard.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.customer.assigneeBoard.domain.CustomerAssigneeBoard;
import ks54team01.customer.assigneeBoard.mapper.CustomerAssigneeBoardMapper;
import ks54team01.customer.assigneeBoard.service.CustomerAssigneeBoardService;
import ks54team01.system.util.PageInfo;
import ks54team01.system.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CustomerAssigneeBoardServiceImpl implements CustomerAssigneeBoardService{

	private final CustomerAssigneeBoardMapper customerAssigneeBoardMapper;
	
	/**
	 * 양수게시글상세조회
	 */
	@Override
	public CustomerAssigneeBoard getAssigneeBoardInfoByCode(String assigneeBoardNum) {
		
		CustomerAssigneeBoard assigneeBoardInfo = customerAssigneeBoardMapper.getAssigneeBoardInfoByCode(assigneeBoardNum);
		
		return assigneeBoardInfo;
	}
	
	/**
	 * 양수게시글목록조회
	 */
	@Override
	public PageInfo<CustomerAssigneeBoard> getAssigneeBoardList(Pageable pageable) {	
		// 마지막 페이지를 구하기 위해 전체 행의 개수 조회
		int contentRowCount = customerAssigneeBoardMapper.getAssigneeBoardCount();
		
		List<CustomerAssigneeBoard> assigneeBoardList = customerAssigneeBoardMapper.getAssigneeBoardList(pageable);
		
		log.info("contentRowCount: {}", contentRowCount);
		log.info("assigneeBoardList: {}", assigneeBoardList);
		
		return new PageInfo<>(assigneeBoardList, pageable, contentRowCount);
	}
}
