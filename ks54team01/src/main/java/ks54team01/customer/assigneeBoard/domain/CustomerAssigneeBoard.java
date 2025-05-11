package ks54team01.customer.assigneeBoard.domain;

import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.customer.register.domain.CustomerMember;
import lombok.Data;

@Data
public class CustomerAssigneeBoard {

	private String assigneeBoardNum;
	private String customerId;
	private String productCategoryNum;
	private String productName;
	private int rentalFee;
	private int remainingMonths;
	private String desiredInstallArea;
	private int quantity;
	private String assigneeContent;
	private String boardValidDate;
	private String registerDate;
	private String revisionDate;
	
	private CustomerMember customerMember;
	
	private ProductInfoCategory productInfoCategory;
}
