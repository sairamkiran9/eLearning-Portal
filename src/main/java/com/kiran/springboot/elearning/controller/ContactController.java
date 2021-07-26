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

import com.kiran.springboot.elearning.model.Contact;
import com.kiran.springboot.elearning.service.ContactService;


@Controller
public class ContactController {
	@Autowired
	private ContactService service;

	@GetMapping("/contacts")
	public String viewHomePage(Model model) {
		List<Contact> listContact = service.listAll();
		model.addAttribute("listContacts", listContact);
		return "contact";
	}

	@GetMapping("/newContact")
	public String showNewContactForrm(Model model) {
		Contact contact = new Contact();
		model.addAttribute("contact", contact);
		return "newContact";
	}

	@PostMapping(value = "/saveContact")
	public String saveContact(@ModelAttribute("contact") Contact contact) {
		service.save(contact);
		return "redirect:/contacts";
	}

	@GetMapping("/editContact/{user_id}")
	public ModelAndView showEditContactPage(@PathVariable(name = "user_id") Long user_id) {
		ModelAndView mav = new ModelAndView("editContact");
		Contact contact = service.get(user_id);
		mav.addObject("contact", contact);
		return mav;
	}

	@GetMapping("/deleteContact/{user_id}")
	public String deleteContact(@PathVariable(name = "user_id") Long user_id) {
		service.delete(user_id);
		return "redirect:/contacts";
	}

}
