package com.kiran.springboot.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiran.springboot.elearning.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
