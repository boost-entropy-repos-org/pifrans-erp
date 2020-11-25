package com.pifrans.modules.user.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.pifrans.global.models.User;

public interface EmailService {

	void sendEmail(SimpleMailMessage msg);

	void sendHtmlEmail(MimeMessage msg);

	void sendNewPasswordEmail(User user, String newPassword);
}
