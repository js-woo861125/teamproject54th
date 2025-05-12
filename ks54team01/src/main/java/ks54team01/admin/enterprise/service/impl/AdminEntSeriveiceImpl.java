package ks54team01.admin.enterprise.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.admin.enterprise.domain.AdminEntDetail;
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
	@Override
	public AdminEntDetail getEntDetail(String ceoCode) {
	    return adminEntMapper.getEntDetail(ceoCode);
	}
	
	@Override
	public List<AdminEntList> getSearchEnt(String searchKey, String searchValue) {
		switch (searchKey) {
	    case "ceoCode" -> searchKey = "ec.ent_ceo_no";
	    case "entName" -> searchKey = "ec.ent_nm";
	    case "entContractStatus" -> searchKey = "ct.contract_status";
	}
		List<AdminEntList> entList = adminEntMapper.getSearchEnt(searchKey, searchValue);
		return entList;
	}
}
