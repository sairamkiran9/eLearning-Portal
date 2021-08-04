package com.kiran.springboot.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kiran.springboot.elearning.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
	@Query("select f.f_id from Feedback f inner join User u on f.user_id=u.user_id where f.user_id=?1")
	long getUser(long id);
	
}
