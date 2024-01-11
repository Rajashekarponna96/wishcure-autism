package com.openspace.HospitalMgnt.common.Login;

import java.time.LocalDateTime;

import com.openspace.Model.UserManagement.Role;

public class LoginDto {

	private long id;

	private String userName;

	private String token;

	private boolean ipExist;

	private String firstname;

	private Role role;
	
	private String imagePath;
	
	private String ipaddress;
	
	private LocalDateTime resetTokenExpires;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isIpExist() {
		return ipExist;
	}

	public void setIpExist(boolean ipExist) {
		this.ipExist = ipExist;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public LocalDateTime getResetTokenExpires() {
		return resetTokenExpires;
	}

	public void setResetTokenExpires(LocalDateTime resetTokenExpires) {
		this.resetTokenExpires = resetTokenExpires;
	}
	
	

}
