package dev.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entite.Login;

public interface LoginRepository extends JpaRepository<Login, Integer>{

    Optional<Login> findByLogin(String login);
    
}
