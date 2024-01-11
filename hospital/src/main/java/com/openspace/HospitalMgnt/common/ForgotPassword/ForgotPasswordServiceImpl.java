package com.openspace.HospitalMgnt.common.ForgotPassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.HospitalMgnt.common.Base64Encoding;
import com.openspace.Model.Config.Mail;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
class ForgotPasswordServiceImpl implements ForgotPasswordService {

	@Autowired
	private UserAccountRepository UserAccountRepository;

	@Override
	public void forgotPassword(String email) {
		UserAccount dbUserAccount = UserAccountRepository.findByUsername(email);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.pleaseEnterYourRegisteredMailId);
		}
		String password = dbUserAccount.getPassword();
		Mail mail = new Mail();
		mail.postMail(email, "Password", "Thanks for requesting EZClinic.Your Account Password is : " + Base64Encoding.decodePassword(password)+
				"If you had not requested for a forgot password and feel that the account is compromised, please contact us  immediately at support@ezclinic.com. <br><br><br><br>Cheers, <br>ezClinic Team.  ");
	}

}
