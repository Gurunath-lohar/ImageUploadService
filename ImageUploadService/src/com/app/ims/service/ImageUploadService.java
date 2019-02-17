package com.app.ims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.app.ims.handler.ImageHandler;
import com.app.ims.handler.ImageHandlerResponse;
import com.app.ims.response.ImsResponse;
import com.app.ims.util.ImageServiceUtil;

@Component
public class ImageUploadService {
	
	@Autowired ImageHandler imageHandler;
	
	public ImsResponse uploadImage(MultipartFile file, Boolean fileOverwrite, String user) {
		ImageHandlerResponse handlerResponse = null;
		String errorMessage = null;
		try {			
			handlerResponse = imageHandler.uploadImage(file, fileOverwrite, user);
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}		
		
		return ImageServiceUtil.buildImsResponse(handlerResponse, errorMessage, user);
	}

	public ImsResponse deleteImage(String fileName, String type, String user) {
		ImageHandlerResponse handlerResponse = null;
		String errorMessage = null;
		try {			
			handlerResponse = imageHandler.deleteImage(fileName, type, user);
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}		
		
		return ImageServiceUtil.buildImsResponse(handlerResponse, errorMessage, user);
	}
}
