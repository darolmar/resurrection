package com.edorasware.microservices.expense.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExpenseAppController {

	@RequestMapping("/")
	String home(ModelMap modal) {
		modal.addAttribute("title","Homework1");
		return "index.html";
	}
}
