package com.openspace.HospitalMgnt.common.Login;

import java.net.UnknownHostException;

import com.openspace.Model.Dto.LoginUserTokenExpireTimeDto;
import com.openspace.Model.UserManagement.UserAccount;

public interface LoginService {

	LoginDto userLogin(UserAccount userAccount) throws UnknownHostException;

	void validateOTP(OTPDto otpDto);

	void resendOTP(String adminUserName);

	LoginUserTokenExpireTimeDto expiredDateForUsername(String adminUserName);

}
