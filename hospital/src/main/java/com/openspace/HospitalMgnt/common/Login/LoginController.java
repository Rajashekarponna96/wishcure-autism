package com.openspace.HospitalMgnt.common.Login;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;
import com.openspace.Model.Dto.LoginUserTokenExpireTimeDto;
import com.openspace.Model.UserManagement.UserAccount;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = RestURIConstants.USER_LOGIN, method = RequestMethod.POST)
	public @ResponseBody LoginDto userLogin(@RequestBody UserAccount userAccount) throws UnknownHostException {
		if (userAccount.getUsername() == null) {
			throw new RuntimeException("Enter Valid UserName");
		}
		if (userAccount.getPassword() == null) {
			throw new RuntimeException("Enter Valid Password");
		}
		return loginService.userLogin(userAccount);
	}

	@RequestMapping(value = RestURIConstants.VALIDATE_OTP, method = RequestMethod.POST)
	public @ResponseBody void validateOTP(@RequestBody OTPDto otpDto) {
		loginService.validateOTP(otpDto);
	}
	
	@RequestMapping(value = RestURIConstants.RESEND_OTP, method = RequestMethod.POST)
	public @ResponseBody void resendOTP(@PathVariable("adminUserName") String adminUserName) {
		loginService.resendOTP(adminUserName);
	}

	@RequestMapping(value = RestURIConstants.OTPEXPIRETIME, method = RequestMethod.POST)
	public @ResponseBody LoginUserTokenExpireTimeDto expiredDateForUsername(@PathVariable("adminUserName") String adminUserName) {
		return loginService.expiredDateForUsername(adminUserName);
	}
}
