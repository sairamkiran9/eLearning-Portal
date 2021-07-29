package com.kiran.springboot.elearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kiran.springboot.elearning.model.Course;
import com.kiran.springboot.elearning.service.CourseService;

@Controller
public class CourseController {
	@Autowired
	private CourseService service;

	@GetMapping("/courses")
	public String viewHomePage(Model model) {
		List<Course> listCourse = service.listAll();
		model.addAttribute("listCourses", listCourse);
		return "course";
	}

	@GetMapping("/newCourse")
	public String showNewCourseForrm(Model model) {
		Course course = new Course();
		model.addAttribute("course", course);
		return "newCourse";
	}

	@PostMapping(value = "/saveCourse")
	public String saveCourse(@ModelAttribute("course") Course course) {
		System.out.print(course.getCourse_id());
		service.save(course); 
		return "redirect:/getCourse/"+Long.toString(course.getCourse_id());
	}

	@GetMapping("/editCourse/{course_id}")
	public ModelAndView showEditCoursePage(@PathVariable(name = "course_id") Long course_id) {
		try {
		ModelAndView mav = new ModelAndView("editCourse");
		Course course = service.get(course_id);
		mav.addObject("course", course);
		return mav;
		} catch (Exception e) {
//			e.printStackTrace();
			ModelAndView mav = new ModelAndView("notFound");
			mav.addObject("notFound", "This may be due to Invalid Entry!");
			return mav;
		}
	}

	@GetMapping("/deleteCourse/{course_id}")
	public String deleteAdmin(@PathVariable(name = "course_id") Long course_id) {
		service.delete(course_id);
		return "logout";
	}
	
	@GetMapping("/getCourse/{course_id}")
	public ModelAndView showCoursePage(@PathVariable(name = "course_id") Long course_id) {
		try {
		ModelAndView mav = new ModelAndView("getCourse");
		Course course = service.get(course_id);
		mav.addObject("course", course);
		return mav;
		}catch (Exception e) {
//			e.printStackTrace();
			ModelAndView mav = new ModelAndView("notFound");
			mav.addObject("notFound", "This may be due to Invalid Entry!");
			return mav;
		}
	}
}
