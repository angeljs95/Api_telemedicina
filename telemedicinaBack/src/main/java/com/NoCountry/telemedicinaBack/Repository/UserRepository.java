package com.NoCountry.telemedicinaBack.Repository;

import com.NoCountry.telemedicinaBack.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
