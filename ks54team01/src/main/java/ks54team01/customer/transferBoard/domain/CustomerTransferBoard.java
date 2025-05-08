package ks54team01.customer.transferBoard.domain;

import ks54team01.customer.delivery.domain.CustomerDeliveryList;
import ks54team01.customer.product.domain.CustomerProduct;
import lombok.Data;

@Data
public class CustomerTransferBoard {

	private String transferBoardNum;
	private String customerId;
	private String rentalContractNum;
	private String sellProductsNum;
	private String modelNum;
	private String itemNum;
	private String productCategoryNum;
	private String deliveryInfoNum;
	private String transferTitle;
	private String transferContent;
	private Integer rentalFee;
	private Integer remainingMonths;
	private String contractEndDate;
	private Integer individualGrants;
	private String boardValidDate;
	private String transferRentalProcsssingStatus;
	private String registerDate;
	private String revisionDate;

	private CustomerProduct productInfo;
	
	private CustomerDeliveryList customerDeliveryList;

}