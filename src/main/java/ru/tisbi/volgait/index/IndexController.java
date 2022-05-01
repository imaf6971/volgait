package ru.tisbi.volgait.index;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ru.tisbi.volgait.applications.ApplicationService;

@Controller
public class IndexController {

	private final ApplicationService applicationService;

	public IndexController(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	@GetMapping("/")
	public String index(Model model, Authentication authentication) {
		if (authentication != null) {
			String email = authentication.getName();
			model.addAttribute("applicationsCounter", applicationService.countUserApplications(email));
		}
		return "index";
	}

}
