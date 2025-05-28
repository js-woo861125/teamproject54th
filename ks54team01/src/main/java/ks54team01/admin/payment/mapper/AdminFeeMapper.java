package ks54team01.admin.payment.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import ks54team01.admin.payment.domain.AdminMonthlyFee;

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
    
    /**
     * 개별 매출 건(fee 테이블)의 지급일과 마감 정산 상태를 업데이트합니다.
     * payment_status는 이 로직에서 변경하지 않습니다.
     * @param feeNoList 업데이트할 feeNo 리스트
     * @param currentDate 현재 날짜 (지급일)
     */
    void updateIndividualFeePaymentStatus(@Param("feeNoList") List<String> feeNoList,
                                          @Param("currentDate") LocalDate currentDate);

    /**
     * 월별 집계 데이터(monthly_fee 테이블)의 지급 상태를 업데이트합니다.
     * 이 쿼리는 settlement_month와 ent_ceo_no에 해당하는 레코드 중 pay_status가 '지급예정'인 경우에만 '지급'으로 변경합니다.
     * @param settlementMonth 정산 월
     * @param entCeoNo 업체 대표 번호
     */
    void updateMonthlyFeePaymentStatus(@Param("settlementMonth") String settlementMonth,
                                       @Param("entCeoNo") String entCeoNo);
    
    
    

}
