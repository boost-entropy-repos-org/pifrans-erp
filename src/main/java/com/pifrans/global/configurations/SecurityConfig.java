package com.pifrans.global.configurations;

import java.util.Arrays;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.pifrans.global.securities.JWTAuthenticationFilter;
import com.pifrans.global.securities.JWTAuthorizationFilter;
import com.pifrans.global.securities.JWTSecurity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger LOG = Logger.getLogger(SecurityConfig.class.getName());
	
	private static final String[] PUBLIC_MATCHERS = { "/h2-console/**" };
	private static final String[] PUBLIC_MATCHERS_GET = { "/business/**", "/users/**" };
	private static final String[] PUBLIC_MATCHERS_POST = { "/business", "/auth/forgot/**" };

	@Autowired
	private Environment environment;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTSecurity jwtSecurity;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		LOG.info("configure(HttpSecurity)");
		if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		http.cors().and().csrf().disable();
		http.authorizeRequests().antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll().antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll().antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtSecurity));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtSecurity, userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		LOG.info("configure(AuthenticationManagerBuilder)");
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		LOG.info("corsConfigurationSource()");
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		LOG.info("bCryptPasswordEncoder()");
		return new BCryptPasswordEncoder();
	}
}
