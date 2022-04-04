package com.shop.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final ObjectMapper objectMapper;
	private final RestAuthenticationSuccessHandler successHandler;
	private final RestAuthenticationFailureHandler failureHandler;

	// private final DataSource dataSource;

	// private final UserDetailsService userDetailsService;

	// private final UserRepository userRepository;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("guest").password("{noop}guestPass").roles("GUEST").and()
				.withUser("user").password("{noop}userPass").roles("USER").and().withUser("admin")
				.password("{noop}adminPass").roles("ADMIN");
		// //builder.jdbcAuthentication().dataSource(dataSource).withDefaultSchema().wit
		//       auth
        //         .userDetailsService(userDetailsService)
        //         .passwordEncoder(new BCryptPasswordEncoder());
			// try
			// {
			//    	auth.userDetailsService(username -> userRepository.findByEmail(username));

			// }
			// catch(UserExistsException e)
			// {
			// 	System.outprintln("User "+username+" was not found!\n");
			// }
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.httpBasic();
		http.authorizeRequests()
				.antMatchers("/swagger-ui/index.html#/").permitAll()
				.antMatchers("/webjars/**").permitAll()
				.antMatchers("/swagger-resources/**").permitAll()
				
				.antMatchers(HttpMethod.GET, "/api/users/id").permitAll()
				.antMatchers(HttpMethod.GET, "/api/users/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/users/all").hasAnyRole("USER", "ADMIN")
				.antMatchers(HttpMethod.POST, "/api/users/save").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/users/del").hasRole("ADMIN")
				
				.antMatchers(HttpMethod.GET, "/api/products/id").permitAll()
				.antMatchers(HttpMethod.GET, "/api/products/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/products/all").hasAnyRole("USER", "ADMIN")
				.antMatchers(HttpMethod.POST, "/api/products/save").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/products/del").hasRole("ADMIN")
				
				.antMatchers(HttpMethod.GET, "/api/categories/id").permitAll()
				.antMatchers(HttpMethod.GET, "/api/categories/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/categories/all").hasAnyRole("USER", "ADMIN")
				.antMatchers(HttpMethod.POST, "/api/categories/save").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/categories/del").hasRole("ADMIN")

				.antMatchers(HttpMethod.GET, "/api/orderDetails/id").permitAll()
				.antMatchers(HttpMethod.GET, "/api/orderDetails/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/orderDetails/all").hasAnyRole("USER", "ADMIN")
				.antMatchers(HttpMethod.POST, "/api/orderDetails/save").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/orderDetails/del").hasRole("ADMIN")

				.antMatchers(HttpMethod.GET, "/api/orders/id").permitAll()
				.antMatchers(HttpMethod.GET, "/api/orders/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/orders/all").hasAnyRole("USER", "ADMIN")
				.antMatchers(HttpMethod.POST, "/api/orders/save").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/orders/del").hasRole("ADMIN")
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
