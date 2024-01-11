package com.openspace.PatientManagement.s3;

import java.io.Serializable;
import java.util.ResourceBundle;

public interface AccessS3Bucket extends Serializable {
	public final String SUFFIX = "/";
	ResourceBundle applicationBundle = ResourceBundle.getBundle("application");
	
	
	public final String bucketName     = applicationBundle.getString("s3.bucketName");
	public final String keyName        = applicationBundle.getString("s3.keyName");
	public final String securityKey = applicationBundle.getString("s3.securityKey");
	public final String URL_TO_ACCESS_GLOBLA_EXTERAL_FILES_LOGOS = "http://"+bucketName+".s3.amazonaws.com/";
	public final String URL_TO_ACCESS_GLOBLA_EXTERAL_FILES_SPECS = "https://"+bucketName+".s3.amazonaws.com";
	
	
	
	
}
