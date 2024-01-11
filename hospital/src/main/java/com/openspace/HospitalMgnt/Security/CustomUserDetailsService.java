/*package com.openspace.HospitalMgnt.Security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.openspace.HospitalMgnt.common.Base64Encoding;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.UserManagement.UserAccount;

@Transactional
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccount user = userAccountRepository.findByUsername(username);
		if (user == null) {
			throw new RuntimeException("User Does not Exist");
		}
		return toUserDetails(user);
	}

	private UserDetails toUserDetails(UserAccount user) {
		return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
				.password(Base64Encoding.decodePassword(user.getPassword())).roles(user.getRole().getRoleName().toUpperCase()).build();
	}

}
*/