package com.pifrans.global.configurations;

import java.util.logging.Logger;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.event.LogoutSuccessEvent;

@Configuration
public class LogoutConfig implements ApplicationListener<LogoutSuccessEvent> {
	private static final Logger LOG = Logger.getLogger(LogoutConfig.class.getName());

	@Override
	public void onApplicationEvent(LogoutSuccessEvent event) {
		LOG.info("Usuário " + event.getAuthentication().getName() + " deslogado com sucesso!");

	}

}
