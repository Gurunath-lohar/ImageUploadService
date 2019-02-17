package com.app.ims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.ims.handler.ImageHandler;
import com.app.ims.handler.ImageHandlerResponse;
import com.app.ims.response.ImsResponse;
import com.app.ims.util.ImageServiceUtil;

@Component
public class ImageSearchService {
	
	@Autowired ImageHandler imageHandler;
	
	public ImsResponse downloadImage(String fileName, String type, String user) {
		ImageHandlerResponse handlerResponse = null;
		String errorMessage = null;
		try {
			handlerResponse = imageHandler.getImage(fileName, type, user);
		}
		catch(Exception e) {
			errorMessage = e.getMessage();
		}
		
		return ImageServiceUtil.buildImsResponse(handlerResponse, errorMessage, user);
	}
	
	public ImsResponse searchImages(String keyword, Integer pageNumber, Integer fetchCount, String user) {
		ImageHandlerResponse handlerResponse = null;
		String errorMessage = null;
		try {
			handlerResponse = imageHandler.searchImages(keyword, pageNumber, fetchCount, user);
		}
		catch(Exception e) {
			errorMessage = e.getMessage();
		}
		
		return ImageServiceUtil.buildImsResponse(handlerResponse, errorMessage, user);
	}
}
