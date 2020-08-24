package com.goodchobo.common.model;

import java.util.ArrayList;

import org.springframework.validation.Validator;

import com.goodchobo.common.util.StringUtil;

public abstract class BaseVO implements Validator {
	private String searchStartAt;
	private String searchEndAt;
	private String searchKey;
	private String searchValue1;
	private String searchValue2;
	private String searchValue3;
	private ArrayList<String> searchValueList;
	private String orderColumnName = "";	// 정렬으로 사용할 컬럼(테이블명.컬럼명)
	private String order = "DESC";	// 정렬기준
	private boolean includeCoulmnBool = true;	// SELECT시 컬럼 포함여부
	private int page = 1;	// 현재 페이지
	private int rows = 20;	// 한 페이지에 표시될 목록 갯수
	private int pageXrows;
	private boolean pagingBool = false;	// 쿼리 페이징여부

	public String getSearchStartAt() {
		return searchStartAt;
	}
	public void setSearchStartAt(String searchStartAt) {
		this.searchStartAt = searchStartAt;
	}
	public String getSearchEndAt() {
		return searchEndAt;
	}
	public void setSearchEndAt(String searchEndAt) {
		this.searchEndAt = searchEndAt;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getSearchValue1() {
		return searchValue1;
	}
	public void setSearchValue1(String searchValue1) {
		this.searchValue1 = searchValue1;
	}
	public String getSearchValue2() {
		return searchValue2;
	}
	public void setSearchValue2(String searchValue2) {
		this.searchValue2 = searchValue2;
	}
	public String getSearchValue3() {
		return searchValue3;
	}
	public void setSearchValue3(String searchValue3) {
		this.searchValue3 = searchValue3;
	}
	public ArrayList<String> getSearchValueList() {
		return searchValueList;
	}
	public void setSearchValueList(ArrayList<String> searchValueList) {
		this.searchValueList = searchValueList == null ? new ArrayList<String>() : searchValueList;
	}
	public String getOrderColumnName() {
		return orderColumnName;
	}
	public void setOrderColumnName(String orderColumnName) {
		this.orderColumnName = orderColumnName;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public boolean isIncludeCoulmnBool() {
		return includeCoulmnBool;
	}
	public void setIncludeCoulmnBool(boolean includeCoulmnBool) {
		this.includeCoulmnBool = includeCoulmnBool;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPageXrows() {
		pageXrows = (page - 1) * getRows();	// 0부터 시작해야하므로 -1
		return pageXrows;
	}
	public void setPageXrows(int pageXrows) {
		this.pageXrows = pageXrows;
	}
	public boolean isPagingBool() {
		return pagingBool;
	}
	public void setPagingBool(boolean pagingBool) {
		this.pagingBool = pagingBool;
	}

}
