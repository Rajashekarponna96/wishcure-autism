package com.openspace.HospitalMgnt.common.ChangePassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;

@RestController
public class ChangePasswordController {

	@Autowired
	private ChangePasswordService changePasswordService;

	@RequestMapping(value = RestURIConstants.CHANGEPASSWORD, method = RequestMethod.POST)
	private @ResponseBody void changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
		changePasswordService.changePassword(changePasswordDto);
	}

}
