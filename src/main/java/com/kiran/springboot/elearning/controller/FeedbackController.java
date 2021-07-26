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
import com.kiran.springboot.elearning.service.FeedbackService;

@Controller
public class FeedbackController {
	@Autowired
	private FeedbackService service;

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
	public String saveFeedback(@ModelAttribute("feedback") Feedback feedback) {
		service.save(feedback);
		return "redirect:/feedbacks";
	}

	@GetMapping("/editFeedback/{f_id}")
	public ModelAndView showEditFeedbackPage(@PathVariable(name = "f_id") Long f_id) {
		ModelAndView mav = new ModelAndView("editFeedback");
		Feedback feedback = service.get(f_id);
		mav.addObject("feedback", feedback);
		return mav;
	}

	@GetMapping("/deleteFeedback/{f_id}")
	public String deleteFeedback(@PathVariable(name = "f_id") Long f_id) {
		service.delete(f_id);
		return "redirect:/feedbacks";
	}

}
