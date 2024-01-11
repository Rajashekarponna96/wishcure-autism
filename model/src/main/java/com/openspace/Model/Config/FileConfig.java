package com.openspace.Model.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
//@PropertySource("classpath:fileLocation.properties")
public class FileConfig {
	@Value("${filelocation}")
	private String locationpath;

	public String getLocationpath() {
		return locationpath;
	}

	public void setLocationpath(String locationpath) {
		this.locationpath = locationpath;
	}
}
