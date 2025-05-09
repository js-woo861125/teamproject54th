package ks54team01.enterprise.delivery.domain;

import java.util.List;

import ks54team01.admin.delivery.domain.AdminDelivery;
import ks54team01.admin.delivery.domain.AdminDeliveryInfo;
import lombok.Data;

@Data
public class EnterpriseDelivery {

	private AdminDeliveryInfo adminDeliveryInfo;
	
	private AdminDelivery adminDelivery;
}
