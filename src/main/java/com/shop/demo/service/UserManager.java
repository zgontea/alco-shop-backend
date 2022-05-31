package com.shop.demo.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.shop.demo.model.User;
import com.shop.demo.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserManager implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		if(user.isAdmin()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
	
		return authorities;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void runAtStart() {
//		User user = new User();
//		user.setAdmin(true);
//		user.setEmail("krzys@admin.com");
//		user.setName("Krzys");
//		user.setSurname("Admin");
//		user.setPhone("793130773");
//		user.setPassword(new BCryptPasswordEncoder().encode("123"));
//
//		userRepository.save(user);
//	}

}
