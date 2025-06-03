package ks54team01.admin.payment.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.admin.payment.domain.AdminMonthlyFee;
import ks54team01.admin.payment.domain.PaymentProcessRequest;
import ks54team01.admin.payment.domain.SettlementConfirmRequest;
import ks54team01.admin.payment.mapper.AdminFeeMapper;
import ks54team01.admin.payment.service.AdminFeeService;

@Service
@Transactional
public class AdminFeeServiceImpl implements AdminFeeService{

	 private final AdminFeeMapper adminFeeMapper;

	    // 생성자 주입
	    public AdminFeeServiceImpl(AdminFeeMapper adminFeeMapper) {
	        this.adminFeeMapper = adminFeeMapper;
	    }

	    /**
	     * 지급 처리 로직
	     * fee 테이블의 provision_date를 현재 날짜로, payment_status를 '지급'으로 업데이트
	     * monthly_fee 테이블의 pay_status를 '지급'으로, provision_date를 현재 날짜로 업데이트
	     * 
	     */
	    
	    @Override
	    public void processPayment(PaymentProcessRequest request) {
	        System.out.println("AdminFeeServiceImpl: 지급 처리 시작");
	        // 1. 개별 매출 건 (fee 테이블) 업데이트
	        if (request.getFeeNoList() != null && !request.getFeeNoList().isEmpty()) {
	            adminFeeMapper.updateIndividualFeePaymentStatus(request.getFeeNoList(), request.getCurrentDate());
	            System.out.println("개별 매출 건 지급일 및 마감 상태 업데이트 완료.");
	        } else {
	            System.out.println("지급 처리할 개별 매출 건이 없습니다.");
	        }

	        // 2. 월별 집계 데이터 (monthly_fee 테이블) 업데이트
	        // entCeoNo가 null이거나 비어있을 경우 searchValue 사용
	        String entCeoNoForMonthlyFee = request.getEntCeoNo() != null && !request.getEntCeoNo().isEmpty() ? request.getEntCeoNo() : request.getSearchValue();

	        if (entCeoNoForMonthlyFee != null && !entCeoNoForMonthlyFee.isEmpty()) {
	            // monthly_fee 테이블의 pay_status를 '지급'으로 업데이트
	            adminFeeMapper.updateMonthlyFeePaymentStatus(request.getSettlementMonth(), entCeoNoForMonthlyFee);
	            System.out.println("월별 집계 데이터 지급 상태 업데이트 완료.");
	        } else {
	            System.out.println("월별 집계 데이터를 업데이트할 업체 정보가 없습니다.");
	        }
	        System.out.println("AdminFeeServiceImpl: 지급 처리 완료");
	    }
	    
	    
	    
	    
	    @Override
	    public void saveSettlement(SettlementConfirmRequest request) {
	        System.out.println("--- 정산 데이터 DB 저장 로직 실행 ---");
	        System.out.println("전달받은 request DTO: " + request); // 디버깅용

	        // 1. 현재 monthly_fee_no의 최대 숫자 부분을 조회
	        int currentMaxNumber = adminFeeMapper.getMaxMonthlyFeeNumber();

	        // 저장할 AdminMonthlyFee 객체들을 담을 리스트
	        List<AdminMonthlyFee> monthlyFeeRecordsToSave = new ArrayList<>();

	        // ----------------------------------------------------
	        // 1. '1.월렌탈요금' 항목 생성
	        // ----------------------------------------------------
	        AdminMonthlyFee rentalFee = new AdminMonthlyFee();
	        currentMaxNumber++;
	        rentalFee.setMonthlyFeeNo("monthly_fee_" + currentMaxNumber);
	        rentalFee.setEntCeoNo(request.getEntCeoNo()); // entCeoNo
	        rentalFee.setEntEmpId(request.getEntEmpId());
	        rentalFee.setManagerId(request.getPlatformEmpId());
	        rentalFee.setPaymentDetails("1.월렌탈요금");
	        rentalFee.setPlatformFee(request.getTotalPlatFormFeeRental()); 
	        rentalFee.setEntFee(request.getTotalEntFeeRental());         
	        rentalFee.setPayStatus("지급예정");
	        rentalFee.setProvisionDate(LocalDate.now());
	        rentalFee.setSettlementMonth(request.getSettlementMonth());
	        monthlyFeeRecordsToSave.add(rentalFee);

	        // ----------------------------------------------------
	        // 2. '2.일시불판매' 항목 생성
	        // ----------------------------------------------------
	        AdminMonthlyFee salesFee = new AdminMonthlyFee();
	        currentMaxNumber++;
	        salesFee.setMonthlyFeeNo("monthly_fee_" + currentMaxNumber);
	        salesFee.setEntCeoNo(request.getEntCeoNo()); // ⭐️ entCeoNo 
	        salesFee.setEntEmpId(request.getEntEmpId());
	        salesFee.setManagerId(request.getPlatformEmpId());
	        salesFee.setPaymentDetails("2.일시불판매");
	        salesFee.setPlatformFee(request.getTotalPlatFormFee()); 
	        salesFee.setEntFee(request.getTotalEntFee());         
	        salesFee.setPayStatus("지급예정");
	        salesFee.setProvisionDate(LocalDate.now());
	        salesFee.setSettlementMonth(request.getSettlementMonth());
	        monthlyFeeRecordsToSave.add(salesFee);

	        // ----------------------------------------------------
	        // 3. '3.위약금' 항목 생성
	        // ----------------------------------------------------
	        AdminMonthlyFee penaltyFee = new AdminMonthlyFee();
	        currentMaxNumber++;
	        penaltyFee.setMonthlyFeeNo("monthly_fee_" + currentMaxNumber);
	        penaltyFee.setEntCeoNo(request.getEntCeoNo()); // ⭐️ entCeoNo 
	        penaltyFee.setEntEmpId(request.getEntEmpId());
	        penaltyFee.setManagerId(request.getPlatformEmpId());
	        penaltyFee.setPaymentDetails("3.위약금");
	        penaltyFee.setPlatformFee(request.getTotalPlatFormPenalty()); 
	        penaltyFee.setEntFee(request.getTotalEntPenalty());        
	        penaltyFee.setPayStatus("지급예정");
	        penaltyFee.setProvisionDate(LocalDate.now());
	        penaltyFee.setSettlementMonth(request.getSettlementMonth());
	        monthlyFeeRecordsToSave.add(penaltyFee);

	        // ----------------------------------------------------
	        // 4. '4.총합계' 항목 생성
	        // ----------------------------------------------------
	        AdminMonthlyFee totalSummary = new AdminMonthlyFee();
	        currentMaxNumber++;
	        totalSummary.setMonthlyFeeNo("monthly_fee_" + currentMaxNumber);
	        totalSummary.setEntCeoNo(request.getEntCeoNo()); // ⭐️ entCeoNo 
	        totalSummary.setEntEmpId(request.getEntEmpId());
	        totalSummary.setManagerId(request.getPlatformEmpId());
	        totalSummary.setPaymentDetails("4.총합계");
	        totalSummary.setPlatformFee(request.getTotalNetPlatformFee()); 

	        // 입점업체 최종 지급액 계산 (매출총합 - 플랫폼 수수료 총합)
	        long calculatedEntFeeTotal = request.getTotalApprovedAmount() - request.getTotalNetPlatformFee();
	        totalSummary.setEntFee(calculatedEntFeeTotal); 
	        totalSummary.setPayStatus("지급예정");
	        totalSummary.setProvisionDate(LocalDate.now());
	        totalSummary.setSettlementMonth(request.getSettlementMonth());
	        monthlyFeeRecordsToSave.add(totalSummary);

	        // ----------------------------------------------------
	        // 모든 AdminMonthlyFee 객체를 DB에 삽입
	        // ----------------------------------------------------
	        for (AdminMonthlyFee record : monthlyFeeRecordsToSave) {
	            adminFeeMapper.insertMonthlyFeeDetail(record);
	            System.out.println("monthly_fee 저장 완료: " + record.getMonthlyFeeNo() + " - " + record.getPaymentDetails());
	        }

	        System.out.println("정산 데이터가 성공적으로 처리되었습니다.");
	        
//	        ---------------------- 마감구분 변경
	              
	        String entCeoNoToUse = request.getEntCeoNo();

	        if (entCeoNoToUse == null || entCeoNoToUse.isEmpty()) {
	            System.err.println("WARNING: SettlementConfirmRequest에 entCeoNo가 비어 있습니다.");
	            throw new IllegalArgumentException("업체 대표 번호(entCeoNo)는 필수 값입니다.");
	        }

	        adminFeeMapper.updateFeeClosingSettlementStatus(
	            request.getSettlementMonth(),
	            entCeoNoToUse,
	            "2.마감(정산)완료" // closing_settlement 상태만 변경
	        );

	        
	        System.out.println("DEBUG: 정산 확정 및 개별 매출 건들의 마감 상태가 '2.마감(정산)완료'로 업데이트되었습니다. (지급 상태는 변경 없음)");
	    }


}
