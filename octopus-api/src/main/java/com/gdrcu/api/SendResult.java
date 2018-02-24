package com.gdrcu.api;

import com.gdrcu.OctContext;

public class SendResult {

	private boolean success;
	
	private int code;//原因码，和错误码可以共用
	private String message;
	
	OctContext context;
	
	
	
	
	public OctContext getContext() {
		return context;
	}
	public void setContext(OctContext context) {
		this.context = context;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
