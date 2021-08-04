package com.kiran.springboot.elearning.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "enroll")
public class Enroll {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long enroll_id;
	private Long user_id;
	private Long course_id;
	private String name;
	private String c_name;

	public Enroll() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Enroll(Long user_id, Long course_id, String name, String c_name) {
		super();
		this.user_id = user_id;
		this.course_id = course_id;
		this.name = name;
		this.c_name = c_name;
	}

	public Long getEnroll_id() {
		return enroll_id;
	}

	public void setEnroll_id(Long enroll_id) {
		this.enroll_id = enroll_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getCourse_id() {
		return course_id;
	}

	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

}
