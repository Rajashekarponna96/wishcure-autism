package com.openspace.HospitalMgnt.common.ForgotPassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;

@RestController
public class ForgotPasswordController {

	@Autowired
	private ForgotPasswordService forgotPasswordService;

	@RequestMapping(value = RestURIConstants.USER_FORGOTPASSWORD, method = RequestMethod.GET)
	public @ResponseBody void forgotPassword(@PathVariable String email) {
		forgotPasswordService.forgotPassword(email);
	}

}
