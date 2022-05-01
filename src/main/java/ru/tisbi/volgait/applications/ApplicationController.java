package ru.tisbi.volgait.applications;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/applications")
public class ApplicationController {

	private final String FOLDER = "applications/";

	private final ApplicationService applicationService;

	public ApplicationController(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	@GetMapping
	public String getApplicationList(Model model, Authentication authentication) {
		List<Application> userApplications = applicationService.findApplicationsByEmail(authentication.getName());
		model.addAttribute("applications", userApplications);
		return FOLDER + "list";
	}

	@GetMapping("/{uuid}")
	public String getApplicationInfo(Model model, @PathVariable UUID uuid) {
		Application app = applicationService.getApplicationByUuid(uuid);
		model.addAttribute("app", app);
		return FOLDER + "info";
	}

	@GetMapping("/add")
	public String addApplicationForm(Model model) {
		var applicationForm = new ApplicationForm();
		model.addAttribute("app", applicationForm);
		return FOLDER + "add";
	}

	@PostMapping("/add")
	public String addApplication(@ModelAttribute("app") @Valid ApplicationForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return FOLDER + "add";
		}
		applicationService.addApplication(form);
		return "redirect:/applications";
	}
}
