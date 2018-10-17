/**
 * 
 */
package io.crs.hsys.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import io.crs.hsys.server.model.Registration;

/**
 * @author robi
 *
 */
@Controller
public class AppController {
/*
	@RequestMapping(value="/login")
    public String login(Model model) {
 
        return "login";

    }
   */  
	@RequestMapping("/signup")
	public String registration(Model model) {
		model.addAttribute("user", new Registration());
		return "signup";
	}
}
