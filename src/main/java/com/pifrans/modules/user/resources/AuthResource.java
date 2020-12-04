package com.pifrans.modules.user.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pifrans.global.securities.JWTSecurity;
import com.pifrans.global.securities.UserDetailSecurity;
import com.pifrans.global.services.UserService;
import com.pifrans.modules.user.dto.EmailDTO;
import com.pifrans.modules.user.services.AuthService;

import javassist.tools.rmi.ObjectNotFoundException;

//@RestController
//@RequestMapping(value = "/user/auth")
public class AuthResource {
	@Autowired
	private JWTSecurity jwtSecurity;
	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserDetailSecurity user = UserService.authenticated();
		String token = jwtSecurity.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDTO) {
		try {
			authService.sendNewPassword(objDTO.getEmail());
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.noContent().build();
	}
}
