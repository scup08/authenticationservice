package com.lzh.authenticationservice.model.entity;

public class AuthEntity  {
	
    private Long authId;

    private String authUrl;

    private String authName;

    private String remarks;

    private String authIcon;

    private Long parentAuthId;

    private Integer enabled;
    
    private Integer authLevel;
    
    private Integer sort;
    
    private Integer authType;

	public Long getAuthId() {
		return authId;
	}

	public void setAuthId(Long authId) {
		this.authId = authId;
	}

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getAuthIcon() {
		return authIcon;
	}

	public void setAuthIcon(String authIcon) {
		this.authIcon = authIcon;
	}

	public Long getParentAuthId() {
		return parentAuthId;
	}

	public void setParentAuthId(Long parentAuthId) {
		this.parentAuthId = parentAuthId;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getAuthLevel() {
		return authLevel;
	}

	public void setAuthLevel(Integer authLevel) {
		this.authLevel = authLevel;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getAuthType() {
		return authType;
	}

	public void setAuthType(Integer authType) {
		this.authType = authType;
	}
	
}
