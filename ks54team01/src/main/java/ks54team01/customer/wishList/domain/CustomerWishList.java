package ks54team01.customer.wishList.domain;

import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.customer.product.domain.CustomerProduct;
import ks54team01.customer.member.domain.CustomerMember;
import lombok.Data;

@Data
public class CustomerWishList {

	private String wishListNum;
	private String customerId;
	private String enterpriseCeoNum;
	private String enterpriseEmployeeId;
	private String productsNum;
	private String sellProductsNum;
	private String registerDate;
	
	private int minimumRentalFee;
	private int minimumPrice;
	
	
	private CustomerMember customerMember;
	private CustomerProduct productInfo;
	private ProductInfoCategory productInfoCategory;
}
