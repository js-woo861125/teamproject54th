package ks54team01.customer.payment.scheduler;


import ks54team01.customer.payment.service.CustomerPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;




@Component
@RequiredArgsConstructor
@Slf4j
public class AutoBillingScheduler {

	
	private final CustomerPaymentService customerPaymentService;
	
	
	// 스케쥴링 어노테이션 추가하기 @Scheduled(cron = "* * 1 1 1 *")
	public void scheduledBilling() {
		
		log.info("정기결제 스케쥴러 시작");
		customerPaymentService.autoBillingPayments();
		log.info("정기결제 스케쥴러 종료");
		
	}
	
}
