package com.kiran.springboot.elearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kiran.springboot.elearning.model.Enroll;
import com.kiran.springboot.elearning.repository.EnrollRepository;

@Service
@Transactional
public class EnrollService {
	@Autowired
    private EnrollRepository repo;
     
    public List<Enroll> listAll() {
        return repo.findAll();
    }
     
    public void save(Enroll enroll) {
        repo.save(enroll);
    }
     
    public Enroll get(long id) {
        return repo.findById(id).get();
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
    
    public List<Enroll> getEnroll(Long id){
    	return repo.getEnroll(id);
    }
}
