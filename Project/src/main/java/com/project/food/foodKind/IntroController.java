package com.project.food.foodKind;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IntroController {
	
	@RequestMapping("/intro")
	public String getIntro() {
		System.out.println("A");
		return "intro";
	}
}
