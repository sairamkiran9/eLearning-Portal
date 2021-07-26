package com.kiran.springboot.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiran.springboot.elearning.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
