package com.openspace.HospitalMgnt.common;

import java.util.Random;

public class OTPGeneration {

	public static String generateOTP() {
		Random random = new Random();
		String s = null;
		for (int j = 1; j <= 1; j++) {
			s = Long.toString(Math.abs(random.nextLong()), 36);
			int k = 15 - s.length();
			for (int l = 1; l <= k; l++) {
				s = (new StringBuilder()).append(s).append(random.nextInt(10))
						.toString();
			}

		}
		 
		return s;
		
		
	}
}