package com.kiran.springboot.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiran.springboot.elearning.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
