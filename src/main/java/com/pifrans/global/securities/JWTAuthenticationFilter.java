package com.pifrans.global.securities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pifrans.global.models.Credential;

/**
 * Classe responsável pela autenticação do usuário
 * @author Lucas F. da Silva
 *
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private static final Logger LOG = Logger.getLogger(JWTAuthenticationFilter.class.getName());
	private AuthenticationManager authenticationManager;
	private JWTSecurity jwtSecurity;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTSecurity jwtSecurity) {
		this.authenticationManager = authenticationManager;
		this.jwtSecurity = jwtSecurity;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		LOG.info("attemptAuthentication()");
		try {
			Credential credenciaisDTO = new ObjectMapper().readValue(request.getInputStream(), Credential.class);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(credenciaisDTO.getEmail(), credenciaisDTO.getPassword(), new ArrayList<>());
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			return authentication;
		} catch (IOException e) {
			LOG.severe(e.getMessage());
			return null;
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		LOG.info("successfulAuthentication()");
		String username = ((UserDetailSecurity) authResult.getPrincipal()).getUsername();
		String token = jwtSecurity.generateToken(username);
		response.addHeader("Authorization", "Bearer " + token);
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		LOG.severe("Erro de autenticação!");
        response.setStatus(401);
        response.setContentType("application/json"); 
        response.getWriter().append(json());
	}
	
	private String json() {
        long date = new Date().getTime();
        return "{\"timestamp\": " + date + ", "
            + "\"status\": 401, "
            + "\"error\": \"Não autorizado\", "
            + "\"message\": \"Email ou senha inválidos\", "
            + "\"path\": \"/login\"}";
    }
}
