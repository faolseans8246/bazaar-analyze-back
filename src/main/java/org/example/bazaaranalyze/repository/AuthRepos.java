package org.example.bazaaranalyze.repository;

import org.example.bazaaranalyze.entity.SignupBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthRepos extends JpaRepository<SignupBase, UUID> {

    Optional<SignupBase> findByEmail(String email);
    Optional<SignupBase> findByUsernameOrEmail(String username, String email);
}
