package ks54team01.system.util;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Pageable {
	
	private int currentPage = 1;
	private int rowPerPage = 1;
	private int offset = 0;
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = Math.max(currentPage, this.currentPage);
		
		/*
		 * if(currentPage < 0) { this.currentPage = 1; }
		 */
	}
	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = Math.max(rowPerPage, this.rowPerPage);
		setOffset();
	}
	
	public void setOffset() {
		this.offset = (currentPage - 1) * rowPerPage;
	}
		
}








