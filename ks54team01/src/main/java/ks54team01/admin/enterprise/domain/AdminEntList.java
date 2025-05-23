package ks54team01.admin.enterprise.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AdminEntList {
	
	private String ceoCode;
	private String entId;
	private String entCeoName;
	private String entName;
	private String entBrno;
	private String ceoTelNo;
	private String entEmpId;
	private String entContractStatus;
	private LocalDate contractEndDate;
	
	private LocalDate regDate;
	
}

