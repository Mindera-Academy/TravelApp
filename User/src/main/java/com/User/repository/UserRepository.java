package com.User.repository;

import com.User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByUsername(String username);
    List<User> findByEmail(String email);
    List<User> findByPhoneNumber(String phoneNumber);

}
