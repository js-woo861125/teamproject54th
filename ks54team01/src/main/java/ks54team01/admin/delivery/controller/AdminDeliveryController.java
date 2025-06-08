package ks54team01.admin.delivery.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ks54team01.admin.delivery.domain.AdminDelivery;
import ks54team01.admin.delivery.domain.AdminDeliveryInfo;
import ks54team01.admin.delivery.service.AdminDeliveryService;
import ks54team01.system.util.PageInfo;
import ks54team01.system.util.Pageable;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/delivery")
public class AdminDeliveryController {
	
	private final AdminDeliveryService adminDeliveryService;
	
	
	@GetMapping("/customerDeliveryList")
	@ResponseBody
	public List<AdminDelivery> getCustomerDeliveryList(@RequestParam String custId) {
	    return adminDeliveryService.getDeliveryListByCustId(custId);
	}
	
	
	
	
	
	
	@GetMapping("/deliveryInfoList")
	public String getDeliveryInfoList(@RequestParam(required = false) String searchKey,
									  @RequestParam(required = false) String searchValue,
									  Pageable pageable, Model model) {

	    pageable.setRowPerPage(10);

	    Map<String, Object> searchParamMap = new HashMap<>();
	    searchParamMap.put("pageable", pageable);

	    if (searchKey != null && !searchKey.isEmpty() && searchValue != null && !searchValue.isEmpty()) {
	        searchParamMap.put("searchKey", searchKey);
	        searchParamMap.put("searchValue", searchValue);
	    }

	    PageInfo<AdminDeliveryInfo> deliveryInfoPageList = adminDeliveryService.getDeliveryInfoList(searchParamMap);

	    model.addAttribute("title", "배송정보 목록");
	    model.addAttribute("deliveryInfoList", deliveryInfoPageList.getContents());
	    model.addAttribute("currentPage", deliveryInfoPageList.getCurrentPage());
	    model.addAttribute("lastPage", deliveryInfoPageList.getLastPage());
	    model.addAttribute("startPageNum", deliveryInfoPageList.getStartPageNum());
	    model.addAttribute("endPageNum", deliveryInfoPageList.getEndPageNum());
	    model.addAttribute("rowPerPage", pageable.getRowPerPage());
	    model.addAttribute("contentRowCount", deliveryInfoPageList.getTotalRowCount());
	    model.addAttribute("searchKey", searchKey);
	    model.addAttribute("searchValue", searchValue);

	    return "admin/deliveryInfo/deliveryInfoListView";
	}
	
	
	
	
	
	@GetMapping("/searchDeliveryList")
	public String getSearchDeliveryList(String searchKey, String searchValue, Model model) {
		
		List<AdminDelivery> deliveryList = adminDeliveryService.getSearchDeliveryList(searchKey, searchValue);
		
		model.addAttribute("title", "배송지 목록");
		model.addAttribute("deliveryList", deliveryList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		return "admin/delivery/deliveryListView";
	}
	
	
	// 배송지 조회
	@GetMapping("/deliveryList")
	public String getDeliveryList(Model model) {
		
		List<AdminDelivery> deliveryList = adminDeliveryService.getDeliveryList();
		
		model.addAttribute("title", "배송지 목록");
		model.addAttribute("deliveryList", deliveryList);
		
		return "admin/delivery/deliveryListView";
		
	}
	
	
	
	
	

}
