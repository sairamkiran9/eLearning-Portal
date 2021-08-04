package com.kiran.springboot.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kiran.springboot.elearning.model.Admin;
import com.kiran.springboot.elearning.model.Enroll;
import com.kiran.springboot.elearning.model.Feedback;
import com.kiran.springboot.elearning.model.Login;
import com.kiran.springboot.elearning.model.User;
import com.kiran.springboot.elearning.service.AdminService;
import com.kiran.springboot.elearning.service.FeedbackService;
import com.kiran.springboot.elearning.service.UserService;

@Controller
public class HomeController {
	@Autowired
	private UserService userService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private FeedbackService feedbackService;

	@GetMapping("/")
	public String homePage(Model model) {
		return "index";
	}

	@GetMapping("/login")
	public String userLoginPage(Model model) {
		Login login = new Login();
		model.addAttribute("login", login);
		return "login";
	}

	@GetMapping("/logout")
	public String userLogoutPage(Model model) {
		Login login = new Login();
		model.addAttribute("login", login);
		return "logout";
	}

	@PostMapping(value = "/saveLogin")
	public ModelAndView saveUser(@ModelAttribute("login") Login login) {
		if (login.getRole().equals("Admin")) {
			Admin admin = adminService.get(login.getId());
			if (admin.getPassword().equals(login.getPassword())) {
				ModelAndView mavAdmin = new ModelAndView("adminIndex");
				mavAdmin.addObject("admin", admin);
				return mavAdmin;
			}
		} else if (login.getRole().equals("User")) {
			User user = userService.get(login.getId());
			if (user.getPassword().equals(login.getPassword())) {
				ModelAndView mavUser = new ModelAndView("userIndex");
				mavUser.addObject("user", user);
				return mavUser;
			}
		}
		return null;
	}
}
