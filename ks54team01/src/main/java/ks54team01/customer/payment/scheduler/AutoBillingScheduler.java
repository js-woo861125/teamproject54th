package ks54team01.customer.payment.scheduler;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ks54team01.customer.payment.service.CustomerPaymentService;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class AutoBillingScheduler {

	
	private final CustomerPaymentService customerPaymentService;
	
	
	@Scheduled(cron = "0 0 3 * * ?")
	public void scheduledBilling() {
		
		customerPaymentService.autoBillingPayments();
		
	}
	
}
