package com.kiran.springboot.elearning.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long course_id;
	private String c_name;
	private String c_desp;
	private String c_fees;
	private String c_resource;
	
	
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getCourse_id() {
		return course_id;
	}


	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}


	public String getC_name() {
		return c_name;
	}


	public void setC_name(String c_name) {
		this.c_name = c_name;
	}


	public String getC_desp() {
		return c_desp;
	}


	public void setC_desp(String c_desp) {
		this.c_desp = c_desp;
	}


	public String getC_fees() {
		return c_fees;
	}


	public void setC_fees(String c_fees) {
		this.c_fees = c_fees;
	}


	public String getC_resource() {
		return c_resource;
	}


	public void setC_resource(String c_resource) {
		this.c_resource = c_resource;
	}
	
	
}
