package com.alby.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alby.userservice.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
    
    boolean existsByUsername(String username);
    
}
