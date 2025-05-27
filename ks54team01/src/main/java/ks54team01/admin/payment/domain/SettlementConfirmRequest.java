package ks54team01.admin.payment.domain;

import java.util.List;

import lombok.Data;

@Data
public class SettlementConfirmRequest {
	
		private String settlementMonth;
	    private String searchValue;
	    private String entEmpId;
	    
	    private String entCeoNo;
	    
	    private String platformEmpId; 
	    
	    private String monthlyFeeNo;

	    // 최종 정산 합계 금액들
	    private long totalPlatFormFeeRental; //플렛폼 수수료 렌탈요금합
	    private long totalEntFeeRental;	//입점업체 수수료 렌탈요금합
	    
	    private long totalPlatFormFee; //일반판매 플렛폼수수료합
	    private long totalEntFee; //일반판매 입점업체 수수료합
	    
	    private long totalPlatFormPenalty; //위약금 플렛폼 합
	    private long totalEntPenalty; // 위약금 입점업체 합
	    
	    private long totalNetPlatformFee; // 플렛폼수수료 총합
	    private long totalApprovedAmount; // 입점업체 매출합
	    
	    private long totalCancelledAmount; // 취소합계 (안씀)
	    
	    private long finalSettlementAmount; // 최종 정산 총계 (안씀)
	
	private List<AdminMonthlyFee> adminMonthlyFeeList;
	
}
