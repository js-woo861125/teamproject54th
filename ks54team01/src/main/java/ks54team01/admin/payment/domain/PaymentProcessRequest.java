package ks54team01.admin.payment.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class PaymentProcessRequest {

	    private String settlementMonth; // 정산 월
	    private String searchValue;     // 검색 값 (업체 코드)
	    private String entEmpId;        // 업체 직원 ID
	    private String entCeoNo;        // 업체 대표 코드 (searchValue와 동일하게 사용될 수 있음)
	    private List<String> feeNoList; // 지급 처리할 개별 수수료 번호 리스트
	    private LocalDate currentDate;  // 현재 날짜 (서버에서 설정)
	
}
