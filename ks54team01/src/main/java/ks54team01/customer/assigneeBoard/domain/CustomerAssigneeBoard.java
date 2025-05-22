package ks54team01.customer.assigneeBoard.domain;

import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.admin.productInfo.domain.ProductInfoItem;
import ks54team01.customer.member.domain.CustomerMember;
import ks54team01.customer.product.domain.CustomerProduct;
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
	private ProductInfoItem productInfoItem;
	
	private CustomerProduct customerProduct;
}
