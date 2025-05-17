package ks54team01.admin.enterprise.service.impl;

import java.time.LocalDate;
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

        for (AdminEntList ent : entList) {
	            LocalDate endDate = ent.getContractEndDate();
	            String currentStatus = ent.getEntContractStatus();

            if (endDate != null) {
                // 계약만료 처리
                if (endDate.isBefore(LocalDate.now()) && !"계약만료".equals(currentStatus)) {
                    adminEntMapper.updateContractStatusToExpired(ent.getCeoCode());
                    ent.setEntContractStatus("계약만료");
                }
                // 계약중 처리
                else if (!endDate.isBefore(LocalDate.now()) && !"계약중".equals(currentStatus)) {
                    adminEntMapper.updateContractStatusToActive(ent.getCeoCode());
                    ent.setEntContractStatus("계약중");
                }
            }
        }

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
