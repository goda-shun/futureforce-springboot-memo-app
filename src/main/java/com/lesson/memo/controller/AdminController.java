package com.lesson.memo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.lesson.memo.model.Admin;
import com.lesson.memo.repository.AdminRepository;
import com.lesson.memo.security.AdminDetailService;

@Controller
public class AdminController{

	@Autowired
    private AdminDetailService adminService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AdminRepository adminRepository;

	@GetMapping("/admin/signup")
    public String signUp(Model model) {
        model.addAttribute("admin", new Admin());
        return "signup";
    }
    
    @GetMapping("/admin/signin")
    public String signIn(Model model) {
        model.addAttribute("admin", new Admin());
        return "signin";
    }
    
    @PostMapping("/admin/signup")
    public String register(@ModelAttribute Admin admin) {
    	String encodedPassword = passwordEncoder.encode(admin.getPassword());
    	admin.setPassword(encodedPassword);
    	adminRepository.save(admin);
    	return "redirect:/admin/signin";
    }
}