package ks54team01.admin.payment.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AdminMonthlyFee {

    private String monthlyFeeNo;      // 월별 수수료 번호 (monthly_fee_X 형식)
    private String entCeoNo;          // 업체 대표 번호 (DB NOT NULL)
    private String entEmpId;          // 업체 직원 ID
    private String managerId;         // 플랫폼 직원 ID
    private String paymentDetails;    // 결제 상세 내역 (1.월렌탈요금, 2.일시불판매, 3.위약금, 4.총합계)
    private long platformFee;         // ⭐️ 변경: 플랫폼 수수료 (long 타입으로 변경 권장)
    private long entFee;              // ⭐️ 변경: 업체 수수료 (업체가 가져가는 금액) (long 타입으로 변경 권장)
    private String payStatus;         // 결제 상태 (지급, 미지급, 지급예정 등)
    private LocalDate provisionDate;  // 제공일자 (결제 또는 매출이 발생한 날짜)
    private String settlementMonth;   // 정산 월 (YYYY-MM 형식)



}
