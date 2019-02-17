package com.app.ims.constatnts;

import java.util.Arrays;
import java.util.List;

public class ImsConstants {
	public static final String FILE_STORE_PATH = "\\upload\\images\\";
	public static final String BASE_URL = "http://localhost:8080/ImageUploadService/v0/ims/image/";
	public static final List<String> SUPPORTED_FILE_FORMATS = Arrays.asList(new String[] {"jpg","jpeg","bmp","gif"});
	public static final String QUERY = "?";
	public static final String IMAGE_TYPE = "type=";
	public static final String DEFAULT_PAGE = "1";
	public static final String DEFAULT_FETCHCOUNT = "25";
	public static final String DOT = ".";
	public static final String DATE_FORMAT = "MM/dd/yyyy HH:mm:ss z";
	public static final String AMP = "&";
	public static final String USER = "user=";
	public static final String SEPARATOR = "\\";
}
