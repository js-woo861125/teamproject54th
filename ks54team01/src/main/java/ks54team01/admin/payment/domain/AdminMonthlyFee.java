package ks54team01.admin.payment.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AdminMonthlyFee {

		private String monthlyFeeNo;      // 월별 수수료 번호 
	    private String entCeoNo;          // 업체 대표 번호
	    private String entEmpId;          // 업체 직원 ID 
	    private String managerId;         // 플랫폼직원 ID
	    private String paymentDetails;    // 결제 상세 내역 (예: 1.월렌탈요금, 2.일시불판매, 3.위약금 4.합계)
	    private int platformFee;          // 플랫폼 수수료
	    private int entFee;               // 업체 수수료 (업체가 가져가는 금액)
	    private String payStatus;         // 결제 상태 (지급, 미지급)
	    private LocalDate provisionDate;  // 제공일자 (결제 또는 매출이 발생한 날짜)
	    private String settlementMonth;   // 정산 월 (YYYY-MM 형식)



}
