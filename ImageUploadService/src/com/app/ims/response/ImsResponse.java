package com.app.ims.response;

import java.util.List;

public class ImsResponse {
	private List<ImageResource> imageResource;
	private int totalCount;
	private String errorMessage;
	
	public List<ImageResource> getImageResource() {
		return imageResource;
	}
	public void setImageResource(List<ImageResource> imageResource) {
		this.imageResource = imageResource;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
