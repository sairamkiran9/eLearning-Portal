package com.kiran.springboot.elearning.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contact")
public class Contact {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;
	private String name;
	private String email;
	private String phone_no;
	private String message;
	private Long contact_id;
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getContact_id() {
		return contact_id;
	}
	public void setContact_id(Long contact_id) {
		this.contact_id = contact_id;
	}

	
}
