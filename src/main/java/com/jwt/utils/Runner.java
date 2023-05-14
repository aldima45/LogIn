package com.jwt.utils;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.jwt.models.Authority;
import com.jwt.models.User;
import com.jwt.repositories.AuthorityRepository;
import com.jwt.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Runner implements CommandLineRunner {
	private final UserRepository userRepository;
	private final AuthorityRepository authorityRepository;

	public Runner(UserRepository userRepository, AuthorityRepository authorityRepository) {
		this.userRepository = userRepository;
		this.authorityRepository = authorityRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		if (this.authorityRepository.count() == 0) {
			this.authorityRepository.saveAll(List.of(
					new Authority(AuthorityName.ADMIN),
					new Authority(AuthorityName.READ),
					new Authority(AuthorityName.WRITE)));
			log.info("Authorities values was added!");
		}

		if (this.userRepository.count() == 0) {

			this.userRepository.save(new User("albert", new BCryptPasswordEncoder().encode("2222"),
					(List<Authority>) List.of(this.authorityRepository.findByName(AuthorityName.READ).get())));
			this.userRepository.save(new User("diaz", new BCryptPasswordEncoder().encode("2222"),
					(List<Authority>) List.of(this.authorityRepository.findByName(AuthorityName.WRITE).get())));
			this.userRepository.save(new User("mateos", new BCryptPasswordEncoder().encode("2222"),
					(List<Authority>) List.of(this.authorityRepository.findByName(AuthorityName.ADMIN).get())));
			log.info("Users & relational tabled (N:M) values was added!");
		}
	}
}
