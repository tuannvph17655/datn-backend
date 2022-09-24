package com.datn.repository;

import com.datn.entity.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface UserRepository extends JpaRepository<User, String> {

    @Query("select u from User u where u.active = :active and u.email = :email")
    User findByEmailAndActive(String email, boolean active);
}
