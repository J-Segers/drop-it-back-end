package com.dropit.backend.demo.repository;

import com.dropit.backend.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {


    @Query("SELECT s FROM User s WHERE s.email = ?1")
    Optional<User> findUserByEmail(String email);
}
