package ks54team01.system.util;

import java.util.List;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class PageInfo<T> {
	private List<T> contents;
	private Pageable pageable;
	private int totalRowCount;
	private int currentPage;
	private int lastPage;
	private int startPageNum;
	private int endPageNum;
	
	public PageInfo(List<T> contents, Pageable pageable, int totalRowCount) {
		this.contents = contents;
		this.pageable = pageable;
		this.totalRowCount = totalRowCount;
		pageNumProcess();
	}
	
	private void pageNumProcess() {
		int currentPage = pageable.getCurrentPage();
		int rowPerPage = pageable.getRowPerPage();
		
		int lastPage = (int) Math.ceil((double) totalRowCount / rowPerPage);
		
		int startPageNum = currentPage < 1 ? 1 : 1 + ((currentPage - 1) / 10) * 10;  // 1, 11, 21 .....
		//int endPageNum = lastPage < ((currentPage - 1) / 10) * 10 + 10 ? ((currentPage - 1) / 10) * 10 + 10 : lastPage;  // 10, 20
        int endPageNum = (startPageNum + 9) >= lastPage ? lastPage : (startPageNum + 9);  

		this.lastPage = lastPage;
		this.startPageNum = startPageNum;
		this.endPageNum = endPageNum;
		this.currentPage = currentPage;
		
	}
	
	
}









