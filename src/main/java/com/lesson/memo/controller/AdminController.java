package com.lesson.memo.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.lesson.memo.model.Admin;
import com.lesson.memo.repository.AdminRepository;

@Controller
public class AdminController{


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
    	admin.setCreatedAt(LocalDateTime.now());
    	admin.setUpdatedAt(LocalDateTime.now());
    	adminRepository.save(admin);
    	return "redirect:/admin/signin";
    }
}