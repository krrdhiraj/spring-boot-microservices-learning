package com.elearn.app.repositories;

import com.elearn.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    // load user ko with their userName

    Optional<User> findByEmail(String email);
}
