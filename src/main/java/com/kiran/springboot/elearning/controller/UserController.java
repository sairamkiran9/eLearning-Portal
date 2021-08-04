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

import com.kiran.springboot.elearning.model.User;
import com.kiran.springboot.elearning.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService service;

	@GetMapping("/users")
	public String viewHomePage(Model model) {
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);
		return "User";
	}

	@GetMapping("/newUser")
	public String showNewUserForrm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "newUser";
	}

	@PostMapping(value = "/saveUser")
	public String saveUser(@ModelAttribute("user") User user) {
		service.save(user);
		return "userIndex";
	}

	@GetMapping("/editUser/{user_id}")
	public ModelAndView showEditUserPage(@PathVariable(name = "user_id") Long user_id) {
		try {
			User user = service.get(user_id);
			ModelAndView mav = new ModelAndView("editUser");
			mav.addObject("user", user);
			return mav;
		} catch (Exception e) {
//			e.printStackTrace();
			ModelAndView mav = new ModelAndView("notFound");
			mav.addObject("notFound", "This may be due to Invalid Entry!");
			return mav;
		}
	}

	@GetMapping("/deleteUser/{user_id}")
	public String deleteUser(@PathVariable(name = "user_id") Long user_id) {
		try {
		service.delete(user_id);
		} catch (Exception e) {
//			e.printStackTrace();
			return "logout";
		}
		return "logout";
	}

	@GetMapping("/getUser/{user_id}")
	public ModelAndView showUserPage(@PathVariable(name = "user_id") Long user_id) {
		try {
		User user = service.get(user_id);
		ModelAndView mav = new ModelAndView("getUser");
		mav.addObject("user", user);
		return mav;
		} catch (Exception e) {
//			e.printStackTrace();
			ModelAndView mav = new ModelAndView("notFound");
			mav.addObject("notFound", "This may be due to Invalid Entry!");
			return mav;
		}
	}

}
