package com.jwt.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.models.Authority;
import com.jwt.utils.AuthorityName;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	Optional<Authority> findByName(AuthorityName name);
}