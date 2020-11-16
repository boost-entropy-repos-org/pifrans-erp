package com.pifrans.modules.users.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pifrans.global.exceptions.errors.ObjectNotFoundException;
import com.pifrans.global.models.User;
import com.pifrans.global.repositories.UserRepository;

//@Service
public class AuthService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private EmailService emailService;
	private Random random = new Random();

	public void sendNewPassword(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new ObjectNotFoundException("E-mail não encontrado!");
		}
		String newPass = newPassword();
		user.setPassword(encoder.encode(newPass));
		userRepository.save(user);
		emailService.sendNewPasswordEmail(user, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randonChar();
		}
		return new String(vet);
	}

	private char randonChar() {
		int opt = random.nextInt(3);
		if (opt == 0) { // Gera um número de 0 a 9
			return (char) (random.nextInt(10) + 48);
		} else if (opt == 1) { // Gera uma letra maiúscula
			return (char) (random.nextInt(26) + 65);
		} else { // Gera uma letra minúscula
			return (char) (random.nextInt(26) + 97);
		}
	}
}
