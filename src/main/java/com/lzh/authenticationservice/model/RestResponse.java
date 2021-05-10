package com.lzh.authenticationservice.model;

import java.io.Serializable;

/**
 * 返回给前端的对象
 */
public class RestResponse implements Serializable{

	private static final long serialVersionUID = -8721853992292562823L;
	
	private int code = 0 ;
    private String msg;
    private Object data;
    
    public RestResponse error(int code,String msg) {
		this.code = code;
		this.msg = msg;
		return this;
	}
    
    public RestResponse error(String msg) {
		this.code = -1;
		this.msg = msg;
		return this;
	}
    
    public RestResponse success(String msg) {
		this.msg = msg;
		return this;
	}
    
    public RestResponse success(String msg, Object data) {
    	this.data = data;
		this.msg = msg;
		return this;
	}
    
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
    
}
