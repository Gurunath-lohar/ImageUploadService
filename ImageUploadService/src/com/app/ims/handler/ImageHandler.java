package com.app.ims.handler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.app.ims.constatnts.ImsConstants;
import com.app.ims.util.ImageServiceUtil;

@Component
public class ImageHandler {
	
	public ImageHandlerResponse getImage(String fileName, String type, String user) throws Exception{
		ImageHandlerResponse handlerResponse = new ImageHandlerResponse();
		ImageFile imageFile = searchOrDeleteImageFile(fileName, type, user, false);
		if(imageFile != null) {
			List<ImageFile> images = new ArrayList<>();
			images.add(imageFile);
			handlerResponse.setImages(images);
			handlerResponse.setTotalCount(1);
		}
		
		return handlerResponse;
	}
	
	public ImageHandlerResponse searchImages(String keyword, Integer pageNumber, Integer fetchCount, String user) throws Exception{
		ImageHandlerResponse handlerResponse = new ImageHandlerResponse();
		List<ImageFile> imageFiles = searchImageFiles(keyword, pageNumber, fetchCount, user);
		if(imageFiles != null && !imageFiles.isEmpty()) {
			handlerResponse.setImages(imageFiles);
			handlerResponse.setTotalCount(imageFiles.size());
		}
		
		return handlerResponse;
	}
	
	public ImageHandlerResponse deleteImage(String fileName, String type, String user) throws Exception{
		ImageHandlerResponse handlerResponse = new ImageHandlerResponse();
		ImageFile imageFile = searchOrDeleteImageFile(fileName, type, user, true);
		if(imageFile != null) {
			List<ImageFile> images = new ArrayList<>();
			images.add(imageFile);
			handlerResponse.setImages(images);
			handlerResponse.setTotalCount(1);
		}
		
		return handlerResponse;
	}
	
	// saves the file to disk, checks file format, creates directory if required, throws error if file already exists, overwrites if needed
	public ImageHandlerResponse uploadImage(MultipartFile file, Boolean fileOverwrite, String user) throws Exception {
		ImageHandlerResponse handlerResponse = new ImageHandlerResponse();
		String fileName = file.getOriginalFilename();
		if(ImageServiceUtil.isValidFileFormat(fileName)) {
			Path fileStoreLocation = Paths.get(getFileStoreLocation(user)+fileName).toAbsolutePath().normalize();			
			try {
				if(fileOverwrite) {
					Files.copy(file.getInputStream(), fileStoreLocation, StandardCopyOption.REPLACE_EXISTING);
				}
				else {
					Files.copy(file.getInputStream(), fileStoreLocation);
				}
			}
			catch(Exception e) {
				throw new Exception("Unable to save file, file already exists");
			}
			
			// prepare response 
			ImageFile imageFile = ImageServiceUtil.prepareImageFile(fileName, new Date());
			List<ImageFile> imageFiles = new ArrayList<>();
			imageFiles.add(imageFile);
			handlerResponse.setTotalCount(1);
			handlerResponse.setImages(imageFiles);
		}
		
		return handlerResponse;
	}
	
	// creates directory if doesn't exist
	private String getFileStoreLocation(String user) throws Exception{
		Path fileStoreLocation = Paths.get(ImsConstants.FILE_STORE_PATH + user).toAbsolutePath().normalize();
		try {
			Files.createDirectories(fileStoreLocation);
		}
		catch(Exception e) {
			throw new Exception("Unable to create directory");
		}
		
		return fileStoreLocation.toString() + ImsConstants.SEPARATOR;
	}
	
	private ImageFile searchOrDeleteImageFile(String fileName, String type, String user, boolean delete) throws Exception{
		ImageFile imageFile = null;
		Path fileStoreLocation = Paths.get(ImsConstants.FILE_STORE_PATH + user +
										   ImsConstants.SEPARATOR + fileName +
										   ImsConstants.DOT + type).toAbsolutePath().normalize();
		File file = new File(fileStoreLocation.toString());
		if(file.exists()) {
			imageFile = ImageServiceUtil.prepareImageFile(file);
			try {
				if(delete) {
					file.delete();
					imageFile.setDeleted(true);
				}
				else {
					imageFile.setData(Files.readAllBytes(file.toPath()));
				}
			} catch (IOException e) {
				throw new Exception("Unable to read file");
			}
		}
		else {
			throw new Exception("File not found");
		}
		
		return imageFile;
	}
	
	private List<ImageFile> searchImageFiles(String keyword, int pageNumber, int fetchCount, String user) throws Exception {
		List<ImageFile> imageFiles = null;
		Path fileStoreLocation = Paths.get(ImsConstants.FILE_STORE_PATH + user).toAbsolutePath().normalize();
		File file = new File(fileStoreLocation.toString());
		if(file.exists()) {
			File[] files = file.listFiles();
			if(files != null && files.length > 0 && ImageServiceUtil.isValidPageNumber(files.length, pageNumber, fetchCount)) {
				imageFiles = new ArrayList<>();
				for(File imgfile : files) {		
					if(StringUtils.isEmpty(keyword)) {
						imageFiles.add(ImageServiceUtil.prepareImageFile(imgfile));
					}
					else if(imgfile.getName().indexOf(keyword) != -1){
						imageFiles.add(ImageServiceUtil.prepareImageFile(imgfile));
					}
				}
			}
			else {
				throw new Exception("Files not found");
			}
		}
		else {
			throw new Exception("File store doesn't exist");
		}
		
		return imageFiles;
	}
}
