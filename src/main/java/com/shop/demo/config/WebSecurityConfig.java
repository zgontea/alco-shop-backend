package com.shop.demo.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.shop.demo.jwt.JwtAuthenticationEntryPoint;
import com.shop.demo.jwt.JwtFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource(name = "userService")
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
				.passwordEncoder(encoder());
	}

	@Bean
	public JwtFilter authenticationTokenFilterBean() {
		return new JwtFilter();
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http = http.cors().and().csrf().disable();
		http.authorizeRequests()
				.antMatchers("/swagger-ui/index.html#/").permitAll()
				.antMatchers("/webjars/**").permitAll()
				.antMatchers("/swagger-resources/**").permitAll()

				// .antMatchers(HttpMethod.GET, "/api/users/id").permitAll()
				// .antMatchers(HttpMethod.GET, "/api/users/").permitAll()
				// .antMatchers(HttpMethod.GET, "/api/users/all").hasAuthority("ROLE_ADMIN")
				// .antMatchers(HttpMethod.POST, "/api/users/save").permitAll()
				// .antMatchers(HttpMethod.DELETE, "/api/users/del").permitAll()
				// .antMatchers(HttpMethod.DELETE, "/api/users/del/").permitAll()

				.antMatchers(HttpMethod.GET, "/api/products/id").permitAll()
				.antMatchers(HttpMethod.GET, "/api/products/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/products/filtered").permitAll()
				.antMatchers(HttpMethod.GET, "/api/products/all").permitAll()
				// .antMatchers(HttpMethod.POST, "/api/products/add").hasAuthority("ROLE_ADMIN")
				// .antMatchers(HttpMethod.DELETE, "/api/products/del").hasAuthority("ROLE_ADMIN")
				// .antMatchers(HttpMethod.DELETE, "/api/products/del/").hasAuthority("ROLE_ADMIN")

				// .antMatchers(HttpMethod.GET, "/api/shopping-cart/id").permitAll()
				// .antMatchers(HttpMethod.GET, "/api/shopping-cart/").permitAll()
				// .antMatchers(HttpMethod.GET, "/api/shopping-cart/all").permitAll()
				// .antMatchers(HttpMethod.POST, "/api/shopping-cart/save").permitAll()
				// .antMatchers(HttpMethod.DELETE, "/api/shopping-cart/del").hasAuthority("ROLE_ADMIN")
				// .antMatchers(HttpMethod.DELETE, "/api/shopping-cart/del/").hasAuthority("ROLE_ADMIN")

				.antMatchers(HttpMethod.GET, "/api/categories/id").permitAll()
				.antMatchers(HttpMethod.GET, "/api/categories/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/categories/all").permitAll()
				.antMatchers(HttpMethod.GET, "/api/categories/categoryName").permitAll()
				// .antMatchers(HttpMethod.POST, "/api/categories/save").hasAuthority("ROLE_ADMIN")
				// .antMatchers(HttpMethod.DELETE, "/api/categories/del").hasAuthority("ROLE_ADMIN")

				// .antMatchers(HttpMethod.GET, "/api/orderDetails/id").permitAll()
				// .antMatchers(HttpMethod.GET, "/api/orderDetails/").permitAll()
				// .antMatchers(HttpMethod.GET, "/api/orderDetails/all").permitAll()
				// .antMatchers(HttpMethod.POST, "/api/orderDetails/save").hasAuthority("ROLE_ADMIN")
				// .antMatchers(HttpMethod.DELETE, "/api/orderDetails/del").hasAuthority("ROLE_ADMIN")

				// .antMatchers(HttpMethod.GET, "/api/orders/id").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
				// .antMatchers(HttpMethod.GET, "/api/orders/").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
				// .antMatchers(HttpMethod.GET, "/api/orders/all").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
				// .antMatchers(HttpMethod.POST, "/api/orders/save").permitAll()
				// .antMatchers(HttpMethod.DELETE, "/api/orders/del").hasAuthority("ROLE_ADMIN")

				.antMatchers(HttpMethod.POST, "/auth/login").permitAll()
				.antMatchers(HttpMethod.POST, "/auth/register").permitAll()

				.antMatchers(HttpMethod.GET, "/api/images/**").permitAll()

				.anyRequest().authenticated()

				.and()
				.exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler)
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	}
}
