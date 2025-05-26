package ks54team01.customer.transferBoard.domain;

import java.util.List;

import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.common.file.domain.FileMetaData;
import ks54team01.customer.contract.domain.CustomerContract;
import ks54team01.customer.delivery.domain.CustomerDeliveryList;
import ks54team01.customer.member.domain.CustomerMember;
import ks54team01.customer.payment.domain.CustomerDeliveryInfo;
import ks54team01.customer.product.domain.CustomerProduct;
import ks54team01.customer.product.domain.CustomerSellProduct;
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

	private CustomerMember customerMember;
	
	private CustomerProduct productInfo;
	
	private CustomerDeliveryInfo customerDeliveryInfo;
	private CustomerDeliveryList customerDeliveryList;
	
	private ProductInfoCategory productInfoCategory;
	
	private CustomerContract customerContract;
	private CustomerSellProduct customerSellProduct;
	
	 private FileMetaData mainImageData; 
	 private List<FileMetaData> fileList; 

}