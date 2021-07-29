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

import com.kiran.springboot.elearning.model.Feedback;
import com.kiran.springboot.elearning.model.User;
import com.kiran.springboot.elearning.service.FeedbackService;
import com.kiran.springboot.elearning.service.UserService;

@Controller
public class FeedbackController {
	@Autowired
	private FeedbackService service;

	@Autowired
	private UserService userService;

	@GetMapping("/feedbacks")
	public String viewHomePage(Model model) {
		List<Feedback> listFeedback = service.listAll();
		model.addAttribute("listFeedbacks", listFeedback);
		return "feedback";
	}

	@GetMapping("/newFeedback")
	public String showNewFeedbackForrm(Model model) {
		Feedback feedback = new Feedback();
		model.addAttribute("feedback", feedback);
		return "newFeedback";
	}

	@PostMapping(value = "/saveFeedback")
	public ModelAndView saveFeedback(@ModelAttribute("feedback") Feedback feedback) {
		try {
			service.save(feedback);
			User user = userService.get(feedback.getUser_id());
			ModelAndView mavUser = new ModelAndView("userIndex");
			mavUser.addObject("user", user);
			return mavUser;
		} catch (Exception e) {
//	e.printStackTrace();
			ModelAndView mav = new ModelAndView("notFound");
			mav.addObject("notFound", "Invalid Entry!");
			return mav;
		}
	}

//	@ResponseStatus(HttpStatus.BAD_REQUEST, reason = "Not found")
	@GetMapping("/editFeedback/{f_id}")
	public ModelAndView showEditFeedbackPage(@PathVariable(name = "f_id") Long f_id) {
		try {
			ModelAndView mav = new ModelAndView("editFeedback");
			Feedback feedback = service.get(f_id);
			mav.addObject("feedback", feedback);
			return mav;
		} catch (Exception e) {
//			e.printStackTrace();
			ModelAndView mav = new ModelAndView("notFound");
			mav.addObject("notFound", "You haven't added any feedback yet!");
			return mav;
		}
	}

	@GetMapping("/deleteFeedback/{f_id}")
	public ModelAndView deleteFeedback(@PathVariable(name = "f_id") Long f_id) {
		try {
			Feedback feedback = service.get(f_id);
			User user = userService.get(feedback.getUser_id());
			ModelAndView mav = new ModelAndView("userIndex");
			mav.addObject("user", user);
			service.delete(f_id);
			return mav;
		} catch (Exception e) {
//			e.printStackTrace();
			ModelAndView mav = new ModelAndView("notFound");
			mav.addObject("notFound", "You haven't added any feedback yet!");
			return mav;
		}

	}

	@GetMapping("/getFeedback/{f_id}")
	public ModelAndView showFeedbackPage(@PathVariable(name = "f_id") Long f_id) {
		try {
			ModelAndView mav = new ModelAndView("getFeedback");
			Feedback feedback = service.get(f_id);
			mav.addObject("feedback", feedback);
			return mav;
		} catch (Exception e) {
//			e.printStackTrace();
			ModelAndView mav = new ModelAndView("notFound");
			mav.addObject("notFound", "Invalid Entry!");
			return mav;
		}
	}
	
	@GetMapping("/userFeedback/{user_id}")
	public ModelAndView showUserFeedbackPage(@PathVariable(name = "user_id") Long user_id) {
		try {
			ModelAndView mav = new ModelAndView("getFeedback");
			long f_id = service.getUser(user_id);
			Feedback feedback = service.get(f_id);
			mav.addObject("feedback", feedback);
			return mav;
		} catch (Exception e) {
//			e.printStackTrace();
			ModelAndView mav = new ModelAndView("notFound");
			mav.addObject("notFound", "This may be due to Invalid Entry!");
			return mav;
		}
	}

}
