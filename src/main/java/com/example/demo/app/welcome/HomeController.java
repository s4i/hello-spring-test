package com.example.demo.app.welcome;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping(value={"/", "/welcome/home"})
	private String home(Model model) {
		model.addAttribute("message", "hello springframework."+"[こんにちは春]");
		model.addAttribute("time", new SimpleDateFormat("yyyy/MM/dd").toString());
		return "welcome/home";
	}
}
