package com.ikt.auth.repository;

import com.ikt.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    User getUserByEmail(String email);

    User getUserByEmailAndPassword(String email, String password);

    User getUserById(Long id);

//    List<User> getUsers();

}
