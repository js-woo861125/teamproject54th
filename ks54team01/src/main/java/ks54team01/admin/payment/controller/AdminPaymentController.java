package ks54team01.admin.payment.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ks54team01.admin.enterprise.domain.AdminEntList;
import ks54team01.admin.payment.domain.AdminFee;
import ks54team01.admin.payment.domain.AdminFeeListWrapper;
import ks54team01.admin.payment.domain.AdminMonthlyFee;
import ks54team01.admin.payment.domain.AdminPayment;
import ks54team01.admin.payment.domain.PaymentProcessRequest;
import ks54team01.admin.payment.domain.SettlementConfirmRequest;
import ks54team01.admin.payment.service.AdminFeeService;
import ks54team01.admin.payment.service.AdminPaymentService;
import ks54team01.customer.transferBoard.domain.CustomerTransferBoard;
import ks54team01.system.util.PageInfo;
import ks54team01.system.util.Pageable;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/payment")
public class AdminPaymentController {

	private final AdminPaymentService adminPaymentService;
	private final AdminFeeService adminFeeService;
	
	@GetMapping("/searchPaymentList")
	public String getSearchPaymentList(String searchKey, String searchValue, Model model) {
		
		List<AdminPayment> paymentList = adminPaymentService.getSearchPaymentList(searchKey, searchValue);
		
		model.addAttribute("title", "결제내역");
		model.addAttribute("paymentList", paymentList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		
		return "admin/payment/paymentListView";
	}
	
	
	@GetMapping("/paymentList")
	public String getPaymentList(@RequestParam(required = false) String searchKey,
	                             @RequestParam(required = false) String searchValue,
	                             Pageable pageable,
	                             Model model) {

	    pageable.setRowPerPage(10);

	    Map<String, Object> searchParamMap = new HashMap<>();
	    searchParamMap.put("pageable", pageable);

	    if (searchKey != null && !searchKey.isEmpty() && searchValue != null && !searchValue.isEmpty()) {
	        searchParamMap.put("searchKey", searchKey);
	        searchParamMap.put("searchValue", searchValue);
	    }

	    PageInfo<AdminPayment> paymentPageList = adminPaymentService.getPaymentPageList(searchParamMap);

	    model.addAttribute("title", "실시간 결제내역");
	    model.addAttribute("paymentList", paymentPageList.getContents());
	    model.addAttribute("currentPage", paymentPageList.getCurrentPage());
	    model.addAttribute("lastPage", paymentPageList.getLastPage());
	    model.addAttribute("startPageNum", paymentPageList.getStartPageNum());
	    model.addAttribute("endPageNum", paymentPageList.getEndPageNum());
	    model.addAttribute("rowPerPage", pageable.getRowPerPage());
	    model.addAttribute("contentRowCount", paymentPageList.getTotalRowCount());
	    model.addAttribute("searchKey", searchKey);
	    model.addAttribute("searchValue", searchValue);

	    return "admin/payment/paymentListView";
	}
//	----------------------------------------------------- 구분선 -------------------------------------------------
	
//	입점업체 검색 및 정산월 선택
	@GetMapping("/searchEnterprise")
	public String getSearchEnterprise(@RequestParam(name="searchKey", required = false, defaultValue = "entCeoNo") String searchKey,
			 						@RequestParam(name="searchValue", required = false)String searchValue,
			 						@RequestParam(name="settlementMonth", required = false) String settlementMonth,
			 						Model model) {
		
		// --- settlementMonth가 null 또는 비어있을 경우 현재 월로 설정
        if (settlementMonth == null || settlementMonth.isEmpty()) {
            settlementMonth = YearMonth.now().toString(); // 현재 연월을 "YYYY-MM" 형태로 설정 (예: "2025-05")
        }
        // ----------------------------------------------------------------------

        // 1. 입점업체 목록 조회
        List<AdminEntList> entList = adminPaymentService.getSearchEnt(searchKey, searchValue);

        // 2. (AdminFee) 목록 조회
        List<AdminFee> adminFeeList = adminPaymentService.getAdminPayFee(searchValue, settlementMonth);

        // 3. 월별 정산금액 목록 조회 (처리된 settlementMonth 사용)
        List<AdminMonthlyFee> adminMonthlyFeeList = adminPaymentService.getAdminPaymentCalc(searchValue, settlementMonth);

        // 모델에 데이터 추가 (뷰로 전달)
        model.addAttribute("entList", entList);
        model.addAttribute("searchKey", searchKey);
        model.addAttribute("searchValue", searchValue); // 사용자가 입력했던 검색값 유지
        model.addAttribute("adminFeeList", adminFeeList);
        model.addAttribute("adminMonthlyFeeList", adminMonthlyFeeList);
        model.addAttribute("title", "입점업체목록");

        // --- 처리된 settlementMonth 값을 뷰로 다시 전달 (사용자가 선택했던 월 유지)
        model.addAttribute("settlementMonth", settlementMonth);
        // ----------------------------------------------------------------------

        return "admin/payment/calculateView";
    }
	
	
	// 정산페이지 처음 빈 페이지
	@GetMapping("/calculate")
	public String getAdminPaymentCalc() {
				
		return "admin/payment/calculateView";
	}
	
	// 마감정산 상세
	 @PostMapping("/calculateDeadline")
	    public String getAdminPaymentCalcDetail(
	            @RequestParam(name = "settlementMonth", required = false) String settlementMonth, // 조회 조건: 정산 연월
	            @RequestParam(name = "searchValue", required = false) String searchValue,       // 조회 조건: 검색 값 (업체)  
	            @RequestParam(name = "entEmpId", required = false) String entEmpId,
	            // @ModelAttribute를 사용하여 AdminFee 객체 리스트를 한 번에 받습니다!
	            @ModelAttribute("adminFeeListWrapper") AdminFeeListWrapper adminFeeListWrapper, // 폼의 'adminFeeList[i].필드명' 데이터가 AdminFee 리스트로 바인딩됩니다.   
	            Model model, // 집계 결과를 다음 뷰(HTML)로 보내기 위해 Model 객체 추가
	            RedirectAttributes redirectAttributes) { // 리다이렉트 시 메시지나 데이터를 보낼 때 사용

		        System.out.println("마감 처리 POST 요청 수신:");
		        System.out.println("정산 연월 (조회 조건): " + settlementMonth);
		        System.out.println("검색 값 (업체 조회 조건): " + searchValue);
	       
	        // adminFeeListWrapper에서 실제 AdminFee 리스트를 가져옵니다.
		        
		        List<AdminFee> adminFeeList = adminFeeListWrapper != null ? adminFeeListWrapper.getAdminFeeList() : null;

		        System.out.println("POST 요청으로 수신된 상세 매출 건수: " + (adminFeeList != null ? adminFeeList.size() : 0) + "건");

		        Map<String, Map<String, Object>> summaryMap = new HashMap<>();

		     // 총계 계산을 위한 변수들 (전체 요약)
		        long totalPaymentCount = 0L;        // 총 결제 건수 (모든 거래 건수)
		        long totalCancellationCount = 0L;   // 총 취소 건수 (입점업체 매출 기준)
		        long totalCancelledAmount = 0L;     // 총 취소 금액 (입점업체 매출 기준)
		        long totalApprovedAmount = 0L;      // 총 승인 금액 (매출금액 + 플랫폼수수료)
		        long totalPlatformFeeCancelledAmount = 0L; // 플랫폼 수수료 취소 금액
		        long totalPlatformFeeAll = 0L; // 모든 거래의 platFormFee 총합 (정상/취소 무관)
		        long totalNetPlatformFee = 0L; // 플랫폼 수수료 최종 합계 (totalPlatformFeeAll - totalPlatformFeeCancelledAmount)

		        if (adminFeeList != null) {
		            for (AdminFee fee : adminFeeList) {
		                String paymentDetails = fee.getPaymentDetils();
		                Long entSales = (long) fee.getEntSales();
		                Long platFormFee = (long) fee.getPlatFormFee();
		                String paymentStatus = fee.getPaymentStatus();

		                // 디버깅을 위해 현재 처리 중인 데이터 출력
		                System.out.println("Processing: paymentDetails=" + paymentDetails + ", paymentStatus=" + paymentStatus + ", entSales=" + entSales + ", platFormFee=" + platFormFee);


		                summaryMap.putIfAbsent(paymentDetails, new HashMap<>());
		                Map<String, Object> currentSummary = summaryMap.get(paymentDetails);

		                // 각 매출 구분별 집계 초기화 또는 업데이트
		                currentSummary.put("count", ((Long) currentSummary.getOrDefault("count", 0L)) + 1L); // 총 결제 건수
		                currentSummary.put("totalEntSales", ((Long) currentSummary.getOrDefault("totalEntSales", 0L)) + entSales); // 총 입점업체 순매출 
		                currentSummary.put("totalPlatformFee", ((Long) currentSummary.getOrDefault("totalPlatformFee", 0L)) + platFormFee); // 매출 구분별 플랫폼 수수료 총합

		                // 각 매출 구분별 취소 정보 초기화 (첫 추가 시)
		                currentSummary.putIfAbsent("cancellationCount", 0L);
		                currentSummary.putIfAbsent("cancelledAmount", 0L);

		                // '2.결제후배송전취소(환불)' 상태 처리
		                if ("2.결제후배송전취소(환불)".equals(paymentStatus)) {
		                    // 매출 구분별 취소 건수 및 금액 업데이트
		                    currentSummary.put("cancellationCount", ((Long) currentSummary.get("cancellationCount")) + 1L);
		                    currentSummary.put("cancelledAmount", ((Long) currentSummary.get("cancelledAmount")) + entSales);

		                    // 전체 취소 건수 및 입점업체 매출 기준 취소 금액
		                    totalCancellationCount++;
		                    totalCancelledAmount += entSales;
		                    
		                    // 플랫폼 수수료 취소 금액 업데이트
		                    totalPlatformFeeCancelledAmount += platFormFee;
		                }
		                
		                // 모든 거래의 platFormFee를 무조건 더하여 총합을 계산 (정상/취소 무관)
		                totalPlatformFeeAll += platFormFee;

		                // 전체 총 결제 건수 증가
		                totalPaymentCount++;
		                // 총 승인 금액 증가: 매출 금액 (entSales) + 플랫폼 수수료 (platFormFee)
		                totalApprovedAmount += (entSales + platFormFee); // <-- 이 부분이 핵심 변경
		            }
		        }
		        
		        // 정상 결제 건수 = 총 결제 건수 - 총 취소 건수 (전체)
		        long totalNormalPaymentCount = totalPaymentCount - totalCancellationCount;
		        // 정상 결제 금액 = 총 승인 금액 - 총 취소 금액 (전체 - 이 값은 현재 플랫폼수수료 행에 직접 표시되진 않음)
		        // totalNormalPaymentAmount는 입점업체 매출 기준이므로, 변경된 totalApprovedAmount와 별개로 계산
		        // 필요하다면 totalNetApprovedAmount = totalApprovedAmount - (취소된 매출 + 취소된 수수료) 등으로 계산해야 합니다.
		        // 현재는 totalNormalPaymentAmount가 사용되지 않으므로 그대로 둡니다.
		        long totalNormalPaymentAmount = totalApprovedAmount - totalCancelledAmount;


		        // 최종 플랫폼 수수료 합계 계산: (모든 수수료 총합) - (취소된 수수료 금액)
		        totalNetPlatformFee = totalPlatformFeeAll - totalPlatformFeeCancelledAmount;

		        List<Map<String, Object>> paymentSummaryList = summaryMap.entrySet().stream()
		            .map(entry -> {
		                Map<String, Object> item = entry.getValue();
		                item.put("paymentDetails", entry.getKey());
		                
		                // 각 매출 구분별 정상 결제 건수 및 금액 계산 (상세 테이블용)
		                long count = (Long) item.getOrDefault("count", 0L);
		                long cancellationCount = (Long) item.getOrDefault("cancellationCount", 0L);
		                long totalEntSales = (Long) item.getOrDefault("totalEntSales", 0L);
		                long cancelledAmount = (Long) item.getOrDefault("cancelledAmount", 0L);

		                item.put("normalCount", count - cancellationCount);
		                // 각 매출 구분별 '총 매출' 계산 (정상 매출 + 총 수수료 + 취소 금액)
		                item.put("totalSales", (totalEntSales - cancelledAmount) + (Long) item.getOrDefault("totalPlatformFee", 0L) + cancelledAmount);
		                item.put("normalEntSales", totalEntSales - cancelledAmount);

		                return item;
		            })
		            // 월렌탈요금 -> 일시불판매 -> 위약금 순으로 정렬되도록 커스텀 정렬 추가
		            .sorted(Comparator.comparing((Map<String, Object> m) -> {
		                String details = (String) m.get("paymentDetails");
		                switch (details) {
		                    case "월렌탈요금": return 1;
		                    case "일시불판매": return 2;
		                    case "위약금": return 3;
		                    default: return 99; // 그 외의 매출 구분은 뒤로
		                }
		            }))
		            .collect(Collectors.toList());

		        System.out.println("\n--- 최종 매출 구분별 집계 결과 ---");
		        paymentSummaryList.forEach(summary -> System.out.println(
		            "매출구분: " + summary.get("paymentDetails") +
		            ", 총 건수: " + summary.get("count") +
		            ", 취소 건수: " + summary.get("cancellationCount") +
		            ", 정상 건수: " + summary.get("normalCount") +
		            ", 순매출: " + summary.get("totalEntSales") + // 기존 '총 매출'
		            ", 취소 금액: " + summary.get("cancelledAmount") +
		            ", 정상 매출: " + summary.get("normalEntSales") +
		            ", 총 수수료: " + summary.get("totalPlatformFee") +
		            ", 수정된 총 매출: " + summary.get("totalSales") // <-- 추가된 '수정된 총 매출'
		        ));
		        System.out.println("-------------------------");

		        model.addAttribute("paymentSummaryList", paymentSummaryList);
		        model.addAttribute("settlementMonth", settlementMonth);
		        model.addAttribute("searchValue", searchValue);
		        model.addAttribute("adminFeeList", adminFeeList); // 뷰에서 상세 내역을 다시 보여줄 때 사용
		        model.addAttribute("entEmpId", entEmpId);
		        
		        
		        // 총계 변수들을 모델에 추가 (전체 합계용)
		        model.addAttribute("totalPaymentCount", totalPaymentCount);          // 총 결제 건수
		        model.addAttribute("totalCancellationCount", totalCancellationCount); // 총 취소 건수
		        model.addAttribute("totalCancelledAmount", totalCancelledAmount);     // 입점업체 매출 기준 총 취소 금액
		        model.addAttribute("totalNormalPaymentCount", totalNormalPaymentCount); // 정상 결제 건수(총결제건수 - 총취소건수)
		        model.addAttribute("totalApprovedAmount", totalApprovedAmount);      // 총 승인 금액 (매출금액 + 플랫폼수수료)
		        model.addAttribute("totalNormalPaymentAmount", totalNormalPaymentAmount); // 입점업체 매출 기준 정상 결제 금액
		        model.addAttribute("totalPlatformFeeCancelledAmount", totalPlatformFeeCancelledAmount); // 플랫폼 수수료 취소 금액
		        model.addAttribute("totalPlatformFeeAll", totalPlatformFeeAll); // 모든 플랫폼 수수료의 총합
		        model.addAttribute("totalNetPlatformFee", totalNetPlatformFee); // 최종 플랫폼 수수료 합계 (총합 - 취소금액)


		        return "admin/payment/calculateDetail";
		    }
	 
	 
	 
	 //마감등록
	 @PostMapping("/confirmSettlement")
	    public String confirmSettlement(@ModelAttribute SettlementConfirmRequest request, 
							            RedirectAttributes redirectAttributes) {

	        System.out.println("\n--- 새로운 메서드: 정산 확정 (confirmSettlement) 호출 ---");
	        System.out.println("정산 연월: " + request.getSettlementMonth());
	        System.out.println("검색 업체코드: " + request.getSearchValue());
	        
	        if (request.getEntCeoNo() == null || request.getEntCeoNo().isEmpty()) {
		        request.setEntCeoNo(request.getSearchValue());
		        
		    System.out.println("entCeoNo가 null이어서 searchValue 값으로 설정: " + request.getEntCeoNo());
		    
		    }
	         
	        
	        System.out.println("업체 직원 ID: " + request.getEntEmpId());
	        System.out.println("플랫폼 직원 ID: " + request.getPlatformEmpId());
	        

	        System.out.println("입점업체 수수료 렌탈요금합 (전달됨): " + request.getTotalEntFeeRental());
	        System.out.println("플렛폼 수수료 렌탈요금합 (전달됨): " + request.getTotalPlatFormFeeRental());
	        
	        System.out.println("입점업체 일반판매 수수료합 (전달됨): " + request.getTotalEntFee());
	        System.out.println("플렛폼 일반판매 수수료합(전달됨): " + request.getTotalPlatFormFee());

	        System.out.println("입점업체 위약금합 (전달됨): " + request.getTotalEntPenalty());
	        System.out.println("플렛폼 위약금합 (전달됨): " + request.getTotalPlatFormPenalty());
	        

	        System.out.println("입점업체 매출총합 (전달됨): " + request.getTotalApprovedAmount());
	        System.out.println("플랫폼 수수료 총합(전달됨): " + request.getTotalNetPlatformFee());


	        // 실제 정산 DB 저장 로직.
	        adminFeeService.saveSettlement(request); // DTO 전체를 서비스로 넘길 수 있습니다.

	        // 정산 완료 후, 성공 메시지와 함께 다른 페이지로 리다이렉트
	        redirectAttributes.addFlashAttribute("message", "정산이 성공적으로 확정되었습니다.");
	        
	        return "redirect:/admin/payment/searchEnterprise"; // 정산 목록 페이지 
	    }
	 
	 
	 // 지급 처리 요청
	    @PostMapping("/processPayment")
	    public String processPayment(@ModelAttribute PaymentProcessRequest request,
	                                 RedirectAttributes redirectAttributes) {
	        System.out.println("\n--- 지급 처리 POST 요청 수신 ---");
	        System.out.println("정산 연월: " + request.getSettlementMonth());
	        System.out.println("검색 값 (업체): " + request.getSearchValue());
	        System.out.println("업체 직원 ID: " + request.getEntEmpId());
	        System.out.println("업체 대표 코드: " + request.getEntCeoNo());
	        System.out.println("지급 처리할 feeNo 리스트: " + (request.getFeeNoList() != null ? request.getFeeNoList().size() : 0) + "건");

	        // 현재 날짜 설정
	        request.setCurrentDate(LocalDate.now());

	        // 서비스 호출하여 지급 처리
	        adminFeeService.processPayment(request);

	        redirectAttributes.addFlashAttribute("message", "지급 처리가 성공적으로 완료되었습니다.");
	        
	        // 지급 처리 후 다시 해당 월과 업체로 리다이렉트하여 업데이트된 상태를 보여줌
	        redirectAttributes.addAttribute("settlementMonth", request.getSettlementMonth());
	        redirectAttributes.addAttribute("searchValue", request.getSearchValue());
	        return "redirect:/admin/payment/searchEnterprise";
	    }
	 
	

	
}