package ks54team01.admin.enterprise.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.admin.enterprise.domain.AdminEntList;
import ks54team01.admin.enterprise.mapper.AdminEntMapper;
import ks54team01.admin.enterprise.service.AdminEntListService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminEntSeriveiceImpl implements AdminEntListService{
	

	private final AdminEntMapper adminEntMapper;

	@Override
	public List<AdminEntList> getEntList() {
		
		List<AdminEntList> entList = adminEntMapper.getEntList();
		
		return entList;
	}
	
}
