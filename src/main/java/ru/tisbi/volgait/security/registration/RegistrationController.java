package ru.tisbi.volgait.security.registration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

	private final String FOLDER = "security/";

	private final RegistrationService registrationService;

	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	@GetMapping("/registration")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new RegistrationForm());
		return FOLDER + "registration";
	}

	@PostMapping("/registration")
	public String register(@ModelAttribute("user") @Valid RegistrationForm form, BindingResult bindingResult,
			HttpServletRequest httpRequest) {
		if (bindingResult.hasErrors()) {
			return FOLDER + "registration";
		}
		registrationService.register(form);
		authenticateNewUser(form, httpRequest);
		return "redirect:/";
	}

	private void authenticateNewUser(RegistrationForm user, HttpServletRequest httpRequest) {
		try {
			httpRequest.login(user.getEmail(), user.getPassword());
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}
