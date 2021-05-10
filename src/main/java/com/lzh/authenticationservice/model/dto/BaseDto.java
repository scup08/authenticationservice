package com.lzh.authenticationservice.model.dto;

import java.io.Serializable;

public class BaseDto implements Serializable{
	
	private Integer pageNum=1;
	private Integer pageSize=10;
	private Boolean isPaging = false;
	private Long total=0L;
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Boolean getIsPaging() {
		return isPaging;
	}
	public void setIsPaging(Boolean isPaging) {
		this.isPaging = isPaging;
	}
	
}
