package com.app.ims.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

import com.app.ims.constatnts.ImsConstants;
import com.app.ims.handler.ImageFile;
import com.app.ims.handler.ImageHandlerResponse;
import com.app.ims.response.ImageResource;
import com.app.ims.response.ImsResponse;

public class ImageServiceUtil {
	public static ImsResponse buildImsResponse(ImageHandlerResponse handlerResponse, String errorMessage, String user) {
		ImsResponse imsResponse = new ImsResponse();
		if(StringUtils.isEmpty(errorMessage)) {
			imsResponse.setTotalCount(handlerResponse.getTotalCount());		
			List<ImageResource> imageResoucres = new ArrayList<>();
			for(ImageFile imageFile : handlerResponse.getImages()) {
				ImageResource imageResource = new ImageResource();
				imageResource.setFileName(imageFile.getFileName());
				imageResource.setLastModifiedDate(imageFile.getLastModifiedDate());
				imageResource.setImageData(imageFile.getData());
				imageResource.setDeleted(imageFile.isDeleted());
				imageResource.setImageUrl(ImsConstants.BASE_URL + imageFile.getFileName()+
						ImsConstants.QUERY + ImsConstants.IMAGE_TYPE+imageFile.getType()+
						ImsConstants.AMP + ImsConstants.USER+user);
				imageResoucres.add(imageResource);
			}
			imsResponse.setImageResource(imageResoucres);
		}
		else {
			imsResponse.setErrorMessage(errorMessage);
		}
		
		return imsResponse;
	}
	
	public static final ImageFile prepareImageFile(File file) {
		return prepareImageFile(file.getName(), new Date(file.lastModified()));
	}
	
	public static final ImageFile prepareImageFile(String fileName, Date lastModifiedDate) {
		ImageFile imageFile = new ImageFile();
		SimpleDateFormat sdf = new SimpleDateFormat(ImsConstants.DATE_FORMAT);
		imageFile.setFileName(fileName.substring(0, fileName.lastIndexOf(".")));
		imageFile.setType(fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()));
		imageFile.setLastModifiedDate(sdf.format(lastModifiedDate));
		
		return imageFile;
	}
	
	// if file name empty, doesn't contain . and not in list of supported file formats, then throw error
	public static boolean isValidFileFormat(String fileName) throws Exception {
		if(StringUtils.isEmpty(fileName) || !(fileName.indexOf(".") != -1) || 
				! ImsConstants.SUPPORTED_FILE_FORMATS.contains(getFileExtension(fileName))) {
			throw new Exception("File format is not supported");
		}
		
		return true;
	}
	
	public static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()).toLowerCase();
	}
	
	public static boolean isValidPageNumber(int totalFiles, int pageNumber, int fetchCount) {
		int totalPages = Math.max((int) Math.ceil((double) totalFiles / (double) fetchCount), 1);
		if(pageNumber <= totalPages) {
			return true;
		}
		
		return false;
	}
}
