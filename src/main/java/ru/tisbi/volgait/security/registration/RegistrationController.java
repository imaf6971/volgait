package ru.tisbi.volgait.security.registration;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

	private final RegistrationService registrationService;

	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	@GetMapping("/registration")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new RegistrationForm());
		return "registration";
	}

	@PostMapping("/registration")
	public String register(@ModelAttribute("user") @Valid RegistrationForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		registrationService.register(form);
		return "redirect:/";
	}

}
