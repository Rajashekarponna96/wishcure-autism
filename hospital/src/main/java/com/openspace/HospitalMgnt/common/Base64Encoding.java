package com.openspace.HospitalMgnt.common;

import org.apache.tomcat.util.codec.binary.Base64;

public class Base64Encoding {

	public static String encodePassword(String password) {
		// encoding byte array into base 64
		byte[] encoded = Base64.encodeBase64(password.getBytes());

		return new String(encoded);
	}

	public static String decodePassword(String encodepassword) {
		// decoding byte array into base64
		byte[] decoded = Base64.decodeBase64(encodepassword.getBytes());
		return new String(decoded);
	}
	
	public static void main(String args[])
	{
		//System.out.println(encodePassword("flag"));
		//System.out.println(encodePassword("update"));
		//System.out.println(encodePassword("delete"));
	}
	

}
