package ru.tisbi.volgait.applications;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/applications")
public class ApplicationController {
	
	private final ApplicationService applicationService;

	public ApplicationController(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}
	
	@GetMapping
	public String getApplicationList(Model model, Authentication authentication) {
		model.addAttribute("applications", applicationService.findApplicationsByEmail(authentication.getName()));
		return "applications";
	}
}
