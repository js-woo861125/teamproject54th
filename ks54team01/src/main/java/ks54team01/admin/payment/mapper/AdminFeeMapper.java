package ks54team01.admin.payment.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import ks54team01.admin.payment.domain.AdminMonthlyFee;
import ks54team01.admin.payment.domain.SettlementConfirmRequest;

@Mapper
@Repository
public interface AdminFeeMapper {

	 // monthly_fee_no의 최대 숫자 부분을 가져오는 메소드
    Integer getMaxMonthlyFeeNumber();

    // AdminMonthlyFee 객체 하나를 monthly_fee 테이블에 삽입하는 메소드

    int insertMonthlyFeeDetail(AdminMonthlyFee adminMonthlyFee);

    
    // 개별 매출 건들의 closing_settlement 상태를 업데이트하는 메서드 선언
    void updateFeeClosingSettlementStatus(
    		   	@Param("settlementMonth") String settlementMonth,
    	        @Param("entCeoNo") String entCeoNo, // ⭐ 파라미터 이름을 entCeoNo로 변경 ⭐
    	        @Param("status") String status
    );
    
    

}
