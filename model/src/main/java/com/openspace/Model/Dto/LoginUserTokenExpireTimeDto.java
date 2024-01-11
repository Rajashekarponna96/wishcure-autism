package com.openspace.Model.Dto;

import java.time.LocalDateTime;

public class LoginUserTokenExpireTimeDto {
	
	private String username;
	
	private LocalDateTime resetTokenExpires;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDateTime getResetTokenExpires() {
		return resetTokenExpires;
	}

	public void setResetTokenExpires(LocalDateTime resetTokenExpires) {
		this.resetTokenExpires = resetTokenExpires;
	}

	
}
