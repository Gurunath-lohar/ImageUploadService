package com.app.ims.handler;

import java.util.List;

public class ImageHandlerResponse {
	private int totalCount;
	private List<ImageFile> images;
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public List<ImageFile> getImages() {
		return images;
	}
	public void setImages(List<ImageFile> images) {
		this.images = images;
	}
}
