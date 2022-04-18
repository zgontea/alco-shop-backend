package com.shop.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.demo.repo.UserRepository;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final ObjectMapper objectMapper;
	private final RestAuthenticationSuccessHandler successHandler;
	private final RestAuthenticationFailureHandler failureHandler;
	private final UserRepository userRepository;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> userRepository
				.findByEmail(username)
				.orElseThrow(
						() -> new UsernameNotFoundException(
                                String.format("User: %s, not found", username)
						)
				)).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http = http.cors().and().csrf().disable();
		http.httpBasic();
		http.authorizeRequests()
				.antMatchers("/swagger-ui/index.html#/").permitAll()
				.antMatchers("/webjars/**").permitAll()
				.antMatchers("/swagger-resources/**").permitAll()

				.antMatchers(HttpMethod.GET, "/api/users/id").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.GET, "/api/users/").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.GET, "/api/users/all").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.POST, "/api/users/save").permitAll()
				.antMatchers(HttpMethod.DELETE, "/api/users/del").permitAll()

				.antMatchers(HttpMethod.GET, "/api/products/id").permitAll()
				.antMatchers(HttpMethod.GET, "/api/products/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/products/all").permitAll()
				.antMatchers(HttpMethod.POST, "/api/products/save").permitAll()//hasAuthority("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/products/del").hasAuthority("ADMIN")

				.antMatchers(HttpMethod.GET, "/api/categories/id").permitAll()
				.antMatchers(HttpMethod.GET, "/api/categories/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/categories/all").permitAll()
				.antMatchers(HttpMethod.POST, "/api/categories/save").permitAll()//hasAuthority("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/categories/del").hasAuthority("ADMIN")

				.antMatchers(HttpMethod.GET, "/api/orderDetails/id").permitAll()
				.antMatchers(HttpMethod.GET, "/api/orderDetails/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/orderDetails/all").permitAll()
				.antMatchers(HttpMethod.POST, "/api/orderDetails/save").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/orderDetails/del").hasAuthority("ADMIN")

				.antMatchers(HttpMethod.GET, "/api/orders/id").hasAnyAuthority("USER", "ADMIN")
				.antMatchers(HttpMethod.GET, "/api/orders/").hasAnyAuthority("USER", "ADMIN")
				.antMatchers(HttpMethod.GET, "/api/orders/all").hasAnyRole("USER", "ADMIN")
				.antMatchers(HttpMethod.POST, "/api/orders/save").hasAnyAuthority("USER", "ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/orders/del").hasAuthority("ADMIN")

				.antMatchers(HttpMethod.POST, "/api/login/logIn").permitAll()

				.and().addFilter(authenticationFilter())
				.exceptionHandling()
				.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
	}

	public AuthenticationFilter authenticationFilter() throws Exception {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(objectMapper);
		authenticationFilter.setAuthenticationSuccessHandler(successHandler);
		authenticationFilter.setAuthenticationFailureHandler(failureHandler);
		authenticationFilter.setAuthenticationManager(super.authenticationManager());
		return authenticationFilter;
	}
}
