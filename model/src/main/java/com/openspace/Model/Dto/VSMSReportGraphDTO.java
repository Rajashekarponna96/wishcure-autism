package com.openspace.Model.Dto;

import java.util.List;

public class VSMSReportGraphDTO {

	private double vsmsQuotient;
	private String socialAge;
	private List<VSMSChildPerformanceDTO> vsmsChildPerformanceDTOList;

	public double getVsmsQuotient() {
		return vsmsQuotient;
	}

	public void setVsmsQuotient(double vsmsQuotient) {
		this.vsmsQuotient = vsmsQuotient;
	}

	public List<VSMSChildPerformanceDTO> getVsmsChildPerformanceDTOList() {
		return vsmsChildPerformanceDTOList;
	}

	public void setVsmsChildPerformanceDTOList(List<VSMSChildPerformanceDTO> vsmsChildPerformanceDTOList) {
		this.vsmsChildPerformanceDTOList = vsmsChildPerformanceDTOList;
	}
	
	

	public String getSocialAge() {
		return socialAge;
	}

	public void setSocialAge(String socialAge) {
		this.socialAge = socialAge;
	}

	@Override
	public String toString() {
		return "VSMSReportGraphDTO [vsmsQuotient=" + vsmsQuotient + ", vsmsChildPerformanceDTOList="
				+ vsmsChildPerformanceDTOList + "]";
	}

}
