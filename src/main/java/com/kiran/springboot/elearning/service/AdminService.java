package com.kiran.springboot.elearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kiran.springboot.elearning.model.Admin;
import com.kiran.springboot.elearning.repository.AdminRepository;

@Service
@Transactional
public class AdminService {
	@Autowired
    private AdminRepository repo;
     
    public List<Admin> listAll() {
        return repo.findAll();
    }
     
    public void save(Admin admin) {
        repo.save(admin);
    }
     
    public Admin get(long id) {
        return repo.findById(id).get();
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
}
