package com.kiran.springboot.elearning.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kiran.springboot.elearning.model.Course;
import com.kiran.springboot.elearning.model.Enroll;
import com.kiran.springboot.elearning.model.User;
import com.kiran.springboot.elearning.service.CourseService;
import com.kiran.springboot.elearning.service.EnrollService;
import com.kiran.springboot.elearning.service.UserService;

@Controller
public class EnrollController {
	@Autowired
	private EnrollService enrollService;

	@Autowired
	private UserService userService;

	@Autowired
	private CourseService courseService;

	@GetMapping("/enrolls")
	public String viewHomePage(Model model) {
		List<Enroll> listEnrolls = enrollService.listAll();
		model.addAttribute("listEnrolls", listEnrolls);
		return "enroll";
	}

	@GetMapping("/newEnroll")
	public String showNewEnrollPage(Model model) {
		Enroll enroll=new Enroll();
		model.addAttribute("enroll", enroll);
		return "newEnroll";
	}
	
	@PostMapping(value = "/saveEnroll")
	public String saveEnroll(@ModelAttribute("enroll") Enroll enroll) {
		enrollService.save(enroll);
		return "redirect:/login";
	}
	
	@GetMapping("/enrolled/{enroll_id}")
	public ModelAndView showEditEnrollPage(@PathVariable(name = "enroll_id") Long enroll_id) {
		try {
			ModelAndView mav = new ModelAndView("getEnroll");
			Enroll enrolled = enrollService.get(enroll_id);
			mav.addObject("enrolled", enrolled);
			return mav;
		} catch (Exception e) {
//			e.printStackTrace();
			ModelAndView mav = new ModelAndView("notFound");
			mav.addObject("notFound", "Invalid Entry!");
			return mav;
		}
	}

	@GetMapping("/enroll/{user_id}")
	public ModelAndView showEnrolledPage(@PathVariable(name = "user_id") Long user_id) {
		try {
			ModelAndView mav = new ModelAndView("getEnroll");
			User user = userService.get(user_id);
			List<Enroll> enrolled = enrollService.getEnroll(user_id);
			mav.addObject("enrolled", enrolled);
			mav.addObject("user", user);
			return mav;
		} catch (Exception e) {
//			e.printStackTrace();
			ModelAndView mav = new ModelAndView("notFound");
			mav.addObject("notFound", "Invalid Entry!");
			return mav;
		}
	}

	@GetMapping("/unEnroll/{enroll_id}")
	public String deleteEnroll(@PathVariable(name = "enroll_id") Long enroll_id) {
		enrollService.delete(enroll_id);
		return "index";
	}
	
	@GetMapping("/getToEnroll/{user_id}")
	public ModelAndView showGetEnrolledPage(@PathVariable(name = "user_id") Long user_id) {
		try {
			ModelAndView mav = new ModelAndView("getToEnroll");
			User user = userService.get(user_id);
			List<Enroll> enrolled = enrollService.getEnroll(user_id);
			List<Course> courses = courseService.listAll();
			Map<String,Course> map=new HashMap<String,Course>();  
			for(Course course:courses) {
				map.put(course.getC_name().toLowerCase(), course);
			}
			System.out.println(map);
			for(Enroll enroll:enrolled) {
				if(map.get(enroll.getC_name().toLowerCase()) != null) {
					map.remove(enroll.getC_name().toLowerCase());
				}
			}
			List<Course> courseList = new ArrayList<Course>();
			for(Course course: map.values()) {
				courseList.add(course);
			}
			mav.addObject("courses", courseList);
			mav.addObject("user", user);
			return mav;
		} catch (Exception e) {
//			e.printStackTrace();
			ModelAndView mav = new ModelAndView("notFound");
			mav.addObject("notFound", "Invalid Entry!");
			return mav;
		}
	}
}
