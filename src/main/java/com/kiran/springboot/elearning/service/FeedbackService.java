package com.kiran.springboot.elearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kiran.springboot.elearning.model.Feedback;
import com.kiran.springboot.elearning.repository.FeedbackRepository;

@Service
@Transactional
public class FeedbackService {
	@Autowired
    private FeedbackRepository repo;
     
    public List<Feedback> listAll() {
        return repo.findAll();
    }
     
    public void save(Feedback feedback) {
        repo.save(feedback);
    }
     
    public Feedback get(long id) {
        return repo.findById(id).get();
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
}
