//package com.acl.config;
//
//import com.acl.dao.entity.UserEntity;
//import com.acl.dao.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//	private final UserRepository userRepository;
//	private final PasswordEncoder passwordEncoder;
//
//	public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//		this.userRepository = userRepository;
//		this.passwordEncoder = passwordEncoder;
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		if (userRepository.count() == 0) {
//			UserEntity user = new UserEntity("admin", passwordEncoder.encode("admin@123"), "ADMIN");
//			userRepository.save(user);
//		}
//	}
//}
