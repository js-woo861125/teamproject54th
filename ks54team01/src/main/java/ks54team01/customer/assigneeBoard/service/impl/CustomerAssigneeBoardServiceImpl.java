package ks54team01.customer.assigneeBoard.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks54team01.admin.productInfo.domain.ProductInfoCategory;
import ks54team01.admin.productInfo.mapper.AdminProductInfoMapper;
import ks54team01.customer.assigneeBoard.domain.CustomerAssigneeBoard;
import ks54team01.customer.assigneeBoard.mapper.CustomerAssigneeBoardMapper;
import ks54team01.customer.assigneeBoard.service.CustomerAssigneeBoardService;
import ks54team01.system.util.PageInfo;
import ks54team01.system.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CustomerAssigneeBoardServiceImpl implements CustomerAssigneeBoardService{

	private final CustomerAssigneeBoardMapper customerAssigneeBoardMapper;
	private final AdminProductInfoMapper adminProductInfoMapper;
	
	/**
	 * 마감일 지난 게시글 삭제
	 */
    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
	public void removeAssigneeBoardByDeadLine() {
        customerAssigneeBoardMapper.removeAssigneeBoardByDeadLine();
	}
	
	@Override
	public boolean removeMyAssigneeBoard(String assigneeBoardNum) {
		
		int deleted = customerAssigneeBoardMapper.removeMyAssigneeBoard(assigneeBoardNum);
		
		boolean isDel = deleted > 0 ? true : false;
		
		return isDel;
	}
	
	@Override
	public void modifyAssigneeBoard(CustomerAssigneeBoard customerAssigneeBoard) {
		
		customerAssigneeBoardMapper.modifyAssigneeBoard(customerAssigneeBoard);	
	}
	
	@Override
	public void addAssigneeBoard(CustomerAssigneeBoard customerAssigneeBoard) {
		
		String assigneeBoardNum =  "assignee_" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
		customerAssigneeBoard.setAssigneeBoardNum(assigneeBoardNum);	
		customerAssigneeBoardMapper.addAssigneeBoard(customerAssigneeBoard);
	}
	
	@Override
	public List<CustomerAssigneeBoard> selectAssigneeBoardProduct(String productCategoryNum) {

		List<CustomerAssigneeBoard> productList = customerAssigneeBoardMapper.getProduct(productCategoryNum);
		
		return productList;
	}
	
	/**
	 *  양수 게시글 등록 시 카테고리 선택
	 */
	@Override
	public List<CustomerAssigneeBoard> selectAssigneeBoardCategory(String mdCategoryNo) {
		

		List<CustomerAssigneeBoard> cateItemList = customerAssigneeBoardMapper.getMiddleCategory(mdCategoryNo);
		
	    return cateItemList;
	}
	
	/**
	 * 양수 게시글 등록
	 */
	@Override
	public List<ProductInfoCategory> addAssigneeBoard() {

		List<ProductInfoCategory> categoryList = adminProductInfoMapper.getCategoryList();
		
		return categoryList;
	}
	
	/**
	 * 내 양수 게시글 목록 조회
	 */
	@Override
	public List<CustomerAssigneeBoard> getMyAssigneeBoardList(String customerId) {
		List<CustomerAssigneeBoard> myAssigneeBoardList = customerAssigneeBoardMapper.getMyAssigneeBoardList(customerId);
		return myAssigneeBoardList;
	}
	
	
	/**
	 * 양수게시글상세조회
	 */
	@Override
	public CustomerAssigneeBoard getAssigneeBoardInfoByCode(String assigneeBoardNum) {
		
		CustomerAssigneeBoard assigneeBoardInfo = customerAssigneeBoardMapper.getAssigneeBoardInfoByCode(assigneeBoardNum);
		
		return assigneeBoardInfo;
	}
	
	/**
	 * 양수게시글목록조회
	 */
	@Override
	public PageInfo<CustomerAssigneeBoard> getAssigneeBoardList(Map<String, Object> searchParamMap) {	
		
		// 전체 행의 개수 조회
		int contentRowCount = customerAssigneeBoardMapper.getAssigneeBoardCount(searchParamMap);
		
		List<CustomerAssigneeBoard> assigneeBoardList = customerAssigneeBoardMapper.getAssigneeBoardList(searchParamMap);
		
		Pageable pageable = (Pageable) searchParamMap.get("pageable");
		
		log.info("contentRowCount: {}", contentRowCount);
		log.info("assigneeBoardList: {}", assigneeBoardList);
		
		return new PageInfo<>(assigneeBoardList, pageable, contentRowCount);
	}
}
