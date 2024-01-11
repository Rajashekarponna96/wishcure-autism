package com.openspace.HospitalMgnt.Therapist.Holidays;

import com.openspace.HospitalMgnt.common.Base64Encoding;

public class Ex {

	public static void main(String[] args) {
		System.out.println(Base64Encoding.decodePassword("T3BlbkAxMjM="));
		System.out.println(Base64Encoding.encodePassword("1ju7thu4behhq65"));
		System.out.println(Base64Encoding.decodePassword("T3BlbkAxMjM="));
	}
}
