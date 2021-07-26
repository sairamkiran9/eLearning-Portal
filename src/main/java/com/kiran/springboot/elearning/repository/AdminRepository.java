package com.kiran.springboot.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiran.springboot.elearning.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
