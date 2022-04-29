package ru.tisbi.volgait.applications;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/add")
	public String addApplicationForm(Model model) {
		var applicationForm = new ApplicationForm();
		model.addAttribute("app", applicationForm);
		return "add-application";
	}
	
	@PostMapping("/add")
	public String addApplication(@ModelAttribute("app") @Valid ApplicationForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "applications";
		}
		applicationService.addApplication(form);
		return "redirect:/applications";
	}
}
