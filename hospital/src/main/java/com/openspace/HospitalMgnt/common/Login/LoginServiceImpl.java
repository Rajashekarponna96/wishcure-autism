package com.openspace.HospitalMgnt.common.Login;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.HospitalMgnt.common.Base64Encoding;
import com.openspace.HospitalMgnt.common.OTPGeneration;
import com.openspace.Model.Config.Mail;
import com.openspace.Model.DoctorManagement.Person;
import com.openspace.Model.Dto.LoginUserTokenExpireTimeDto;
import com.openspace.Model.PatientMgnt.Repositories.PersonRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private PersonRepository personRepository;

	@Override
	public LoginDto userLogin(UserAccount userAccount) throws UnknownHostException {

		UserAccount dbUserAccount = userAccountRepository.findByUsername(userAccount.getUsername());
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.enterAValidUsername);
		} else {

			if (dbUserAccount.isActive() == false) {
				if (dbUserAccount.getRole().getRoleName().equals("Super Admin")) {
					List<UserAccount> companyUsers = userAccountRepository
							.findByCompany_Id(dbUserAccount.getCompany().getId());
					/*
					 * for (UserAccount userAccount2 : companyUsers) {
					 * //userAccount2.setActive(false);
					 * //userAccountRepository.save(userAccount2); }
					 */

				}

			}

			if (dbUserAccount.isActive() == false) {
				throw new RuntimeException(ErrorMessageHandler.inactiveUserCanNotBeLoggedIn);
			}

			/*
			 * if(dbUserAccount.getCompany()!=null){ Person
			 * dbPerson=personRepository.findByEmail(userAccount.getUsername());
			 * UserAccount dbUserAccount2 =
			 * userAccountRepository.findByUsername(dbPerson.getCreatedByUser())
			 * ; System.out.println(dbUserAccount2.getUsername());
			 * if(dbUserAccount2.isActive() == false){ throw new
			 * RuntimeException("InActive Company Users Can't be Logged!"); } }
			 */

			if (dbUserAccount.getCompany() != null) {
				Long companyId = dbUserAccount.getCompany().getId();
				List<UserAccount> dbUserAccount2 = userAccountRepository.findByCompany_Id(companyId);
				for (UserAccount userAccount2 : dbUserAccount2) {
					if (userAccount2.getRole().getRoleName().equals("Super Admin")) {
						if (userAccount2.isActive() == false) {
							throw new RuntimeException(ErrorMessageHandler.inactiveCompanyUserCanNotBeLoggedIn);
						}
					}
				}

			}
			if (!Base64Encoding.decodePassword(dbUserAccount.getPassword()).equals(userAccount.getPassword())) {
				throw new RuntimeException(ErrorMessageHandler.invalidPassword);
			}

			InetAddress localhost = InetAddress.getLocalHost();
			String ip = localhost.getHostAddress().trim();
			//if (dbUserAccount.isOtpEntered() != false) {
				if (dbUserAccount.getLoginIp() != null) {
					if (!dbUserAccount.getLoginIp().equals(userAccount.getLoginIp())) {
						dbUserAccount.setLoginIp(userAccount.getLoginIp());
						//dbUserAccount.setOtpEntered(false);
						dbUserAccount.setToken(OTPGeneration.generateOTP());
						dbUserAccount.setResetTokenExpires(LocalDateTime.now().plusMinutes(10));
						dbUserAccount.setIpExist(false);
						userAccountRepository.save(dbUserAccount);
						//718
					/*
					 * Mail mail = new Mail(); mail.postMail(userAccount.getUsername(), "OTP",
					 * "In order to ensure that your account is safe, we enforce OTP ( one time password ) policy any time you login from a different device or login for the first time. A separate email with your OTP will be sent to the registered email address. Anytime you use a different device, for security reasons, we will be sending OTP to your registered email address.<br><br><br>  Your OTP is   "
					 * + dbUserAccount.getToken());
					 */
						//718
					} else {
						dbUserAccount.setIpExist(true);
						userAccountRepository.save(dbUserAccount);
					}
				} else {
					dbUserAccount.setLoginIp(ip);
					dbUserAccount.setToken(OTPGeneration.generateOTP());
					dbUserAccount.setResetTokenExpires(LocalDateTime.now().plusMinutes(10));
					dbUserAccount.setIpExist(false);
					userAccountRepository.save(dbUserAccount);
					//718
				/*
				 * Mail mail = new Mail(); mail.postMail(userAccount.getUsername(), "OTP",
				 * "In order to ensure that your account is safe, we enforce OTP ( one time password ) policy any time you login from a different device or login for the first time. A separate email with your OTP will be sent to the registered email address. Anytime you use a different device, for security reasons, we will be sending OTP to your registered email address.<br><br><br>  Your OTP is   "
				 * + dbUserAccount.getToken());
				 */
					//718

				}
//			} else {
//				dbUserAccount.setLoginIp(userAccount.getLoginIp());
//				dbUserAccount.setOtpEntered(false);
//				dbUserAccount.setToken(OTPGeneration.generateOTP());
//				dbUserAccount.setResetTokenExpires(LocalDateTime.now().plusMinutes(10));
//				dbUserAccount.setIpExist(false);
//				userAccountRepository.save(dbUserAccount);
//				Mail mail = new Mail();
//				mail.postMail(userAccount.getUsername(), "OTP",
//						"In order to ensure that your account is safe, we enforce OTP ( one time password ) policy any time you login from a different device or login for the first time. A separate email with your OTP will be sent to the registered email address. Anytime you use a different device, for security reasons, we will be sending OTP to your registered email address.<br><br><br>  Your OTP is   "
//								+ dbUserAccount.getToken());
//
//				userAccountRepository.save(dbUserAccount);
//
//			}

			LoginDto loginDto = new LoginDto();
			loginDto.setId(dbUserAccount.getId());
			loginDto.setUserName(dbUserAccount.getUsername());
			loginDto.setToken(dbUserAccount.getToken());
			loginDto.setIpExist(dbUserAccount.isIpExist());
			loginDto.setIpaddress(userAccount.getLoginIp());
			loginDto.setRole(dbUserAccount.getRole());
			loginDto.setResetTokenExpires(dbUserAccount.getResetTokenExpires());
			Person dbPerson = personRepository.findByEmail(dbUserAccount.getUsername());
			loginDto.setFirstname(dbPerson.getFirstName());
			loginDto.setImagePath(dbPerson.getProfilePic());
			return loginDto;
		}
	}

	@Override
	public void validateOTP(OTPDto otpDto) {
		// TODO Auto-generated method stub
		UserAccount dbUserAccount = userAccountRepository.findByUsername(otpDto.getEmail());
		if (dbUserAccount == null) {
			throw new RuntimeException("User Doesnot  Exist!");
		}

		if (LocalDateTime.now().isBefore(dbUserAccount.getResetTokenExpires())) {
			if (!dbUserAccount.getToken().equals(otpDto.getToken())) {
				throw new RuntimeException(ErrorMessageHandler.invalidOtp);
			} else {
				dbUserAccount.setOtpEntered(true);
				userAccountRepository.save(dbUserAccount);

			}
		}else{
			throw new RuntimeException("Token Expired Please Click Resend OTP......!");
		}

	}

	@Override
	public void resendOTP(String adminUserName) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUserName);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.enterAValidUsername);
		}
		dbUserAccount.setToken(OTPGeneration.generateOTP());
		dbUserAccount.setResetTokenExpires(LocalDateTime.now().plusMinutes(10));
		Mail mail = new Mail();
		mail.postMail(adminUserName, "OTP",
				"In order to ensure that your account is safe, we enforce OTP ( one time password ) policy any time you login from a different device or login for the first time. A separate email with your OTP will be sent to the registered email address. Anytime you use a different device, for security reasons, we will be sending OTP to your registered email address.<br><br><br>  Your OTP is   "
						+ dbUserAccount.getToken());

		userAccountRepository.save(dbUserAccount);

	}
	
	@Override
	public LoginUserTokenExpireTimeDto expiredDateForUsername(String adminUserName){
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUserName);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.enterAValidUsername);
		}
		LoginUserTokenExpireTimeDto loginUserTokenExpireTimeDto=new LoginUserTokenExpireTimeDto();
		loginUserTokenExpireTimeDto.setUsername(dbUserAccount.getUsername());
		loginUserTokenExpireTimeDto.setResetTokenExpires(dbUserAccount.getResetTokenExpires());
		return loginUserTokenExpireTimeDto;
		
	}

}
