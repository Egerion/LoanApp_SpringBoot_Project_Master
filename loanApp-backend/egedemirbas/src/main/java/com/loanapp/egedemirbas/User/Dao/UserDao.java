package com.loanapp.egedemirbas.User.Dao;

import com.loanapp.egedemirbas.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    @Query("select user from User user")
    List<User> findAllUsers();

    User findUserById(Long id);

    User save(User user);

    User findUserByIdentityNumber(Long identityNumber);
}
