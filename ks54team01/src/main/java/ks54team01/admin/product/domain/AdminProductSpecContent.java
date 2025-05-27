package ks54team01.admin.product.domain;

import java.util.List;

import lombok.Data;

@Data
public class AdminProductSpecContent {
	private String specNo;       
    private String specNm;       
    private List<String> detailList;  
    private String specContent;
}
