package de.mediapool.server.mvc.module.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import de.mediapool.server.core.controller.MPController;

@Controller
public class LoginController implements MPController {

	@RequestMapping("/login-page")
	public String login() {
		return "login/login";
	}

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login/login";
	}
}
