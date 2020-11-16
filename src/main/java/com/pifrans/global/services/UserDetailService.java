package com.pifrans.global.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pifrans.global.models.User;
import com.pifrans.global.repositories.UserRepository;
import com.pifrans.global.securities.UserDetailSecurity;

@Service
public class UserDetailService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserDetailSecurity(user.getId(), user.getEmail(), user.getPassword(), user.getProfiles());
	}
}
