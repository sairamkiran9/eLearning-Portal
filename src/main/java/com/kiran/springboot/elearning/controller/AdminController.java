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
import com.kiran.springboot.elearning.model.Admin;
import com.kiran.springboot.elearning.service.AdminService;

@Controller
public class AdminController {
	@Autowired
	private AdminService service;

	@GetMapping("/admins")
	public String viewHomePage(Model model) {
		List<Admin> listAdmin = service.listAll();
		model.addAttribute("listAdmins", listAdmin);
		return "admin";
	}

	@GetMapping("/newAdmin")
	public String showNewAdminForrm(Model model) {
		Admin admin = new Admin();
		model.addAttribute("admin", admin);
		return "newAdmin";
	}

	@PostMapping(value = "/saveAdmin")
	public ModelAndView saveAdmin(@ModelAttribute("admin") Admin admin) {
		try {
			service.save(admin);
			Admin adm = service.get(admin.getAdmin_id());
			ModelAndView mavAdmin = new ModelAndView("adminIndex");
			mavAdmin.addObject("admin", adm);
			return mavAdmin;
		} catch (Exception e) {
//			e.printStackTrace();
			ModelAndView mav = new ModelAndView("notFound");
			mav.addObject("notFound", "This may be due to Invalid Entry!");
			return mav;
		}

	}

	@GetMapping("/editAdmin/{admin_id}")
	public ModelAndView showEditAdminPage(@PathVariable(name = "admin_id") Long admin_id) {

		ModelAndView mav = new ModelAndView("editAdmin");
		Admin admin = service.get(admin_id);
		mav.addObject("admin", admin);
		return mav;
	}

	@GetMapping("/deleteAdmin/{admin_id}")
	public String deleteAdmin(@PathVariable(name = "admin_id") Long admin_id) {
		service.delete(admin_id);
		return "logout";
	}

	@GetMapping("/getAdmin/{admin_id}")
	public ModelAndView showAdminPage(@PathVariable(name = "admin_id") Long admin_id) {
		try {
			ModelAndView mav = new ModelAndView("getAdmin");
			Admin admin = service.get(admin_id);
			mav.addObject("admin", admin);
			return mav;
		} catch (Exception e) {
//				e.printStackTrace();
			ModelAndView mav = new ModelAndView("notFound");
			mav.addObject("notFound", "This may be Invalid Entry!");
			return mav;
		}

	}
}
