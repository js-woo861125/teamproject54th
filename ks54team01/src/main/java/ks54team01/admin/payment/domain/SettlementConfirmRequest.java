package ks54team01.admin.payment.domain;

import java.util.List;

import lombok.Data;

@Data
public class SettlementConfirmRequest {
	
		private String settlementMonth;
	    private String searchValue;
	    private String entEmpId;
	    private String platformEmpId; // 플랫폼직원 ID

	    // 최종 정산 합계 금액들
	    private long totalApprovedAmount;
	    private long totalCancelledAmount;
	    private long totalNetPlatformFee;
	    private long finalSettlementAmount; // 최종 정산 총계
	
	private List<AdminMonthlyFee> adminMonthlyFeeList;
	
}
