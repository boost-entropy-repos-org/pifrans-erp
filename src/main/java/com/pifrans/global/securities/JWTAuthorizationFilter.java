package com.pifrans.global.securities;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Classe responsável pela autorização do usuário
 * @author Lucas F. da Silva
 *
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	private static final Logger LOG = Logger.getLogger(JWTAuthorizationFilter.class.getName());
	private JWTSecurity jwtSecurity;
	private UserDetailsService userDetailsService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTSecurity jwtSecurity, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtSecurity = jwtSecurity;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		
		if (header != null && header.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken token = getAuthentication(header.substring(7));
			if (token != null) {
				SecurityContextHolder.getContext().setAuthentication(token);
			}
		}
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if (jwtSecurity.tokenValido(token)) {
			String username = jwtSecurity.getUsername(token);
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		}
		LOG.severe("Token inválido! " + token);
		return null;
	}
}
