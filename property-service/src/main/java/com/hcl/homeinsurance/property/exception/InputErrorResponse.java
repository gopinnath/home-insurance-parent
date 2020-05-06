package com.hcl.homeinsurance.property.exception;

import java.util.List;

public class InputErrorResponse {
	private List<Object> statusCode;
	private String statusMessage;
	public List<Object> getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(List<Object> statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public InputErrorResponse(List<Object> statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
}
