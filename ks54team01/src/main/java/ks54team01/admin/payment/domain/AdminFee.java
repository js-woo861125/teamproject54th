package ks54team01.admin.payment.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AdminFee {
	
	// 1수수료코드
	private String feeNo;
	// 2결제완료코드
	private String paymentCompletedNo;
	// 3입점업체 대표ID
	private String entCeoNo;
	// 4입점업체 직원ID
	private String entEmpId;
	// 5플랫폼 직원ID
	private String managerId;
	// 6매출구분
	private String paymentDetils;
	// 7플랫폼수수료
	private int platFormFee;
	// 8입접업체 매출
	private int entSales;
	// 9지급예정일
	private LocalDate provisionScheduleDate;
	// 10지급일
	private LocalDate provisionDate;
	// 11정산연월
	private String settlementMonth;
	// 12마감구분
	private String closingSttelement;
	// 13결제후 주문취소(상태)
	private String paymentStatus;
	// 14수정일
	private LocalDate revisionDate;
	
}
