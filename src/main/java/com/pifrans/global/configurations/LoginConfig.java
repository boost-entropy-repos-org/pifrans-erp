package com.pifrans.global.configurations;

import java.util.Date;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

import com.pifrans.global.exceptions.errors.ErrorException;
import com.pifrans.global.models.User;
import com.pifrans.global.services.UserService;

@Configuration
public class LoginConfig implements ApplicationListener<AuthenticationSuccessEvent> {
	private static final Logger LOG = Logger.getLogger(LoginConfig.class.getName());

	@Autowired
	private UserService userService;

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		LOG.info("Usuário " + event.getAuthentication().getName() + " logado com sucesso!");
		try {
			User user = userService.findByEmail(event.getAuthentication().getName());
			user.setLastAccess(user.getCurrentAccess());
			user.setCurrentAccess(new Date(System.currentTimeMillis()));
			userService.save(user);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw new ErrorException(e.getMessage());
		}
	}
}
