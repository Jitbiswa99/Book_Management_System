package com.bookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookStore.dao.UserRepository;
import com.bookStore.entity.User;

@Controller
public class HomeController {
	
	
//	@Autowired
//	private UserRepository userRepository;
//	
//	@GetMapping("/test")
//	@ResponseBody
//	public String test()
//	{
//		
//		User user =new User();
//		user.setName("Biswajit");
//		
//		userRepository.save(user);
//		
//		return "Working";
	}


