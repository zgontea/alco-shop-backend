package com.shop.demo.service;

import java.util.Optional;

import com.shop.demo.model.User;
import com.shop.demo.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

	private final UserRepository userRepository;
	private final static String salt = BCrypt.gensalt();

	@Autowired
	public UserManager(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public Optional<User> findByEmail(String email) {
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

	@EventListener(ApplicationReadyEvent.class)
	public void runAtStart() {
		User user = new User();
		user.setAdmin(true);
		user.setEmail("janekgontarek@gmail.com");
		user.setName("Zbyszko");
		user.setSurname("TrzyCytryny");
		user.setPhone("793130773");
		user.setPassword(BCrypt.hashpw("123", salt));

		userRepository.save(user);
	}

}
