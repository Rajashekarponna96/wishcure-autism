package com.openspace.HospitalMgnt.common.ChangePassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.HospitalMgnt.common.Base64Encoding;
import com.openspace.Model.Config.Mail;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class ChangePasswordServiceImpl implements ChangePasswordService {

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Override
	public void changePassword(ChangePasswordDto changePasswordDto) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(changePasswordDto.getUserName());
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.enterAValidUsername);
		} else {

			if (changePasswordDto.getOldPassword().equals(Base64Encoding.decodePassword(dbUserAccount.getPassword()))) {

				if (!changePasswordDto.getOldPassword().equals(changePasswordDto.getNewPassword())) {

					if (changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmPassword())) {
						dbUserAccount
								.setPassword(Base64Encoding.encodePassword(changePasswordDto.getConfirmPassword()));
						userAccountRepository.save(dbUserAccount);
						Mail mail = new Mail();
						/*mail.postMail(changePasswordDto.getUserName(), "Password change Notification",
								"<h3>Your Password changed successfully.</h3><br><b>Password:</b>"
												+ changePasswordDto.getNewPassword() + "");*/
						
						mail.postMail(changePasswordDto.getUserName(), "Password change Notification",
								"Your password has been successfully changed. If you had not requested for a change in password and feel that the account is compromised, please contact us  immediately at support@ezclinic.com.<br><br><br><br>Cheers, <br>ezClinic Team. ");
					} else {
						throw new RuntimeException(ErrorMessageHandler.passwordMustMatch);
					}

				} else {
					throw new RuntimeException(ErrorMessageHandler.passwordMustBeDifferent);
				}

			} else {
				throw new RuntimeException(ErrorMessageHandler.enterYourCurrentPassword);
			}

		}

	}

}
