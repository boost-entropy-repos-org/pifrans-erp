package com.pifrans.global.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pifrans.global.enums.Profile;
import com.pifrans.global.models.User;
import com.pifrans.global.repositories.UserRepository;
import com.pifrans.global.securities.UserDetailSecurity;

@Service
public class UserService extends GenericService<User> {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(UserService.class.getName());

	@Autowired
	private UserRepository userRepository;

	public static UserDetailSecurity authenticated() {
		LOG.info("authenticated()");
		try {
			return (UserDetailSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			return null;
		}
	}

	@Override
	public User find(Long id) throws Exception {
		UserDetailSecurity user = authenticated();
		if (user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
			throw new AccessDeniedException("Acesso negado!");
		}
		return super.find(id);
	}

	public User findByEmail(String email) throws Exception {
		LOG.info("findByEmail()");
		return userRepository.findByEmail(email);
	}
}
