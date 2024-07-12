package com.NoCountry.telemedicinaBack.Repository;

import com.NoCountry.telemedicinaBack.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

}
