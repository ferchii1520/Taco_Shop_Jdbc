package com.FDMF.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.FDMF.model.Order;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	
	@GetMapping("/current")
	public String showOrderForm(Model model) {
		//model.addAttribute("order", new Order());
		return "orderForm";
		
	}
	
	@PostMapping
	public String processOrder(@ModelAttribute Order order) {
		log.info(order.toString());
		return "redirect:/";
		
	}

}
