package com.example.demo;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping(value="/")
	private String home(Model model) {
		model.addAttribute("message", "hello springframework.");
		model.addAttribute("time", new Date());
		return "home";
	}
}
