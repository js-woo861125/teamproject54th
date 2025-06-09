package ks54team01.enterprise.product.domain;

import java.util.List;

import lombok.Data;

@Data
public class EnterpriseSellProductAllRequest {
	   private List<EnterpriseSellProductRequest> sellProductRequests;
	   private EnterpriseProductQuantity quantity;
	   

}
