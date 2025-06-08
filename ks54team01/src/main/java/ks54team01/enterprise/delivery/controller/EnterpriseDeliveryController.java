package ks54team01.enterprise.delivery.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import ks54team01.enterprise.delivery.domain.EnterpriseDelivery;
import ks54team01.enterprise.delivery.domain.EnterpriseDeliveryInfo;
import ks54team01.enterprise.delivery.service.EnterpriseDeliveryService;
import ks54team01.system.util.PageInfo;
import ks54team01.system.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/enterprise/delivery")
@Slf4j
public class EnterpriseDeliveryController {

	private final EnterpriseDeliveryService enterpriseDeliveryService;
	
	
	
	@PostMapping("/departDelivery")
	public String departDelivery(@RequestParam("delInfoNo") String deliveryNo, RedirectAttributes reAttr) {
		
		EnterpriseDelivery delivery = enterpriseDeliveryService.getDeliveryInfoByCode(deliveryNo);
		
		delivery.getAdminDeliveryInfo().setDelCompany("직접배송");
		
		delivery.getAdminDeliveryInfo().setDelProgress("배송중");
		
		String currentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		delivery.getAdminDeliveryInfo().setDepartDate(currentDateTime);
		
		enterpriseDeliveryService.modifyDelivery(delivery);
		
		reAttr.addAttribute("delInfoNo", delivery.getAdminDeliveryInfo().getDelInfoNo());
		
		return "redirect:/enterprise/delivery/deliveryList";
	}
	
	
	
	
	
	@PostMapping("/completeDelivery")
	public String completeDelivery(@RequestParam("delInfoNo") String deliveryNo, RedirectAttributes reAttr) {
		
		
		EnterpriseDelivery delivery = enterpriseDeliveryService.getDeliveryInfoByCode(deliveryNo);
		
		delivery.getAdminDeliveryInfo().setDelProgress("배송완료");
		
		String currentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		delivery.getAdminDeliveryInfo().setArriveDate(currentDateTime);
		
		enterpriseDeliveryService.modifyDelivery(delivery);
		
		reAttr.addAttribute("delInfoNo", delivery.getAdminDeliveryInfo().getDelInfoNo());
		
		return "redirect:/enterprise/delivery/deliveryList";
	}
	
	
	
	
	
	
	
	@GetMapping("/deliveryList")
	public String getDeliveryList(@RequestParam(required = false) String searchKey,
								  @RequestParam(required = false) String searchValue,
								  Pageable pageable, Model model, HttpSession session) {
		
		String entCeoNo = (String) session.getAttribute("entCeoNo");
		
		
		pageable.setRowPerPage(10);
		
		Map<String, Object> searchParamMap = new HashMap<>();
		searchParamMap.put("entCeoNo", entCeoNo);
	    searchParamMap.put("pageable", pageable);

	    if (searchKey != null && !searchKey.isEmpty() && searchValue != null && !searchValue.isEmpty()) {
	        searchParamMap.put("searchKey", searchKey);
	        searchParamMap.put("searchValue", searchValue);
	    }
		
	    PageInfo<EnterpriseDeliveryInfo> deliveryList = enterpriseDeliveryService.getDeliveryList(searchParamMap);
		
	    model.addAttribute("title", "배송정보 목록");
	    model.addAttribute("deliveryList", deliveryList.getContents());
	    model.addAttribute("currentPage", deliveryList.getCurrentPage());
	    model.addAttribute("lastPage", deliveryList.getLastPage());
	    model.addAttribute("startPageNum", deliveryList.getStartPageNum());
	    model.addAttribute("endPageNum", deliveryList.getEndPageNum());
	    model.addAttribute("rowPerPage", pageable.getRowPerPage());
	    model.addAttribute("contentRowCount", deliveryList.getTotalRowCount());
	    model.addAttribute("searchKey", searchKey);
	    model.addAttribute("searchValue", searchValue);
		
		return "enterprise/delivery/deliveryListView";
	}
}
