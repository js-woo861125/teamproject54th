package ks54team01.admin.transferBoard.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.admin.transferBoard.mapper.AdminTransferBoardMapper;
import ks54team01.admin.transferBoard.service.AdminTransferBoardService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminTransferBoardServiceImpl implements AdminTransferBoardService{

	private final AdminTransferBoardMapper adminTransferBoardMapper;
	
	@Override
	public boolean removeTransferBoard(String transferBoardNum) {
		
		int deleted = adminTransferBoardMapper.removeTransferBoard(transferBoardNum);
		
		boolean isDel = deleted > 0 ? true : false;
		
		return isDel;
	}
}
