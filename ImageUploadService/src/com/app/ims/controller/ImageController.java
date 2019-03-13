package com.app.ims.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.ims.constatnts.ImsConstants;
import com.app.ims.response.ImsResponse;
import com.app.ims.service.ImageSearchService;
import com.app.ims.service.ImageUploadService;
import com.app.ims.service.UserService;

@RestController
@RequestMapping(value="v0/ims/image")
public class ImageController {
	
	@Autowired ImageUploadService imageUploadService;
	
	@Autowired ImageSearchService imageSearchService;
	
	@Autowired UserService userService;
	
	@RequestMapping(value="/{name}", method= {RequestMethod.GET})
	public ImsResponse downloadImage(@PathVariable("name") String name, 
								@RequestParam("type") String type,
								@RequestParam("user") String user) {
		ImsResponse imsResponse = imageSearchService.downloadImage(name, type, user);
		
		return imsResponse;
	} 
	
	@RequestMapping(method= {RequestMethod.GET}) 
	public ImsResponse searchImages(@RequestParam(value="keyword" , required = false) String keyword,
								  @RequestParam(value = "pgn" , defaultValue = ImsConstants.DEFAULT_PAGE) Integer  pageNumber, 
								  @RequestParam(value = "cnt" , defaultValue = ImsConstants.DEFAULT_FETCHCOUNT) Integer fetchCount,
								  @RequestParam("user") String user) { 
		ImsResponse imsResponse = imageSearchService.searchImages(keyword, pageNumber, fetchCount, user);
	  
	  return imsResponse; 
	} 
	
	@RequestMapping(method= {RequestMethod.POST}, consumes=MediaType.MULTIPART_FORM_DATA)
	public ImsResponse uploadImage(@RequestParam("file") MultipartFile file, 
								   @RequestParam("user") String user,
								   @RequestParam(value = "overwrite", defaultValue = "false") Boolean overwrite) {
		ImsResponse imsResponse = imageUploadService.uploadImage(file, overwrite, user);
		userService.addUserFile(user, imsResponse);
		
		return imsResponse;
	}
	
	@RequestMapping(value="/{name}", method= {RequestMethod.DELETE})
	public ImsResponse uploadImage(@PathVariable("name") String name, 
								   @RequestParam("type") String type,
								   @RequestParam("user") String user) {
		ImsResponse imsResponse = imageUploadService.deleteImage(name, type, user);	
		
		return imsResponse;
	}
}
