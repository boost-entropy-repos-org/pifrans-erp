package com.pifrans.global.securities;

import java.util.Date;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Classe responsável pela geração de token e validação do token
 * @author Lucas F. da Silva
 *
 */

@Component
public class JWTSecurity {
	private static final Logger LOG = Logger.getLogger(JWTSecurity.class.getName());
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String generateToken(String username) {
		LOG.info("generateToken()");
		return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + expiration)).signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}

	public boolean tokenValido(String token) {
		LOG.info("tokenValido()");
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date nowDate = new Date(System.currentTimeMillis());
			if (username != null && expirationDate != null && nowDate.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}

	private Claims getClaims(String token) {
		LOG.info("getClaims()");
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		}catch (Exception e) {
			LOG.severe(e.getMessage());
			return null;
		}
	}

	public String getUsername(String token) {
		LOG.info("getUsername()");
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}
}
