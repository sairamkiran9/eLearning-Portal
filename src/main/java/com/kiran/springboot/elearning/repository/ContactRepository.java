package com.kiran.springboot.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiran.springboot.elearning.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
