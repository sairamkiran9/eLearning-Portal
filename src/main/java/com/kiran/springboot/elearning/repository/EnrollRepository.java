package com.kiran.springboot.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kiran.springboot.elearning.model.Enroll;

public interface EnrollRepository extends JpaRepository<Enroll, Long> {
	@Query("select e from Enroll e where e.user_id=?1")
	List<Enroll> getEnroll(Long id);
}
