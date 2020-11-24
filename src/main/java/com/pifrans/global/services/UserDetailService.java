package com.pifrans.global.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pifrans.global.exceptions.errors.ErrorException;
import com.pifrans.global.models.User;
import com.pifrans.global.securities.UserDetailSecurity;

@Service
public class UserDetailService implements UserDetailsService {
	private static final Logger LOG = Logger.getLogger(UserDetailService.class.getName());

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) {
		LOG.info("loadUserByUsername()");
		try {
			User user = userService.findByEmail(email);
			if (user == null) {
				throw new UsernameNotFoundException(email);
			}
			return new UserDetailSecurity(user.getId(), user.getEmail(), user.getPassword(), user.getProfiles());
		} catch (UsernameNotFoundException e) {
			LOG.severe(e.getMessage());
			throw new UsernameNotFoundException(email);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw new ErrorException(e.getMessage());
		}
	}
}
