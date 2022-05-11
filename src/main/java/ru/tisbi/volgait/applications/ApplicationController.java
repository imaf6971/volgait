package ru.tisbi.volgait.applications;

import static ru.tisbi.volgait.applications.DateFilter.MONTH;
import static ru.tisbi.volgait.applications.DateFilter.WEEK;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/applications")
public class ApplicationController {

	private final String FOLDER = "applications/";

	private final ApplicationService applicationService;

	private final AnalythicsService analythicsService;

	public ApplicationController(ApplicationService applicationService, AnalythicsService analythicsService) {
		this.applicationService = applicationService;
		this.analythicsService = analythicsService;
	}

	@GetMapping
	public String getApplicationList(Model model, Authentication authentication) {
		addApplicationListToModel(model, authentication);
		return FOLDER + "list";
	}

	private void addApplicationListToModel(Model model, Authentication authentication) {
		List<Application> userApplications = applicationService.findApplicationsByEmail(authentication.getName());
		model.addAttribute("applications", userApplications);
	}

	@GetMapping("/{uuid}")
	public String getApplicationInfo(Model model, @PathVariable UUID uuid) {
		return getApplicationInfo(model, uuid, WEEK);
	}

	@GetMapping(value = "/{uuid}", params = "filter")
	public String getApplicationInfo(Model model, @PathVariable UUID uuid, @RequestParam DateFilter filter) {
		addApplicationToModel(model, uuid);
		addFiltersToModel(model, filter);
		addRequestsToModel(model, uuid, MONTH);
		return FOLDER + "info";
	}

	private void addFiltersToModel(Model model, DateFilter selectedFilter) {
		model.addAttribute("selectedFilter", selectedFilter.value);
		model.addAttribute("filters", getFilters());
	}

	private Map<DateFilter, String> getFilters() {
		Map<DateFilter, String> filters = new LinkedHashMap<>();
		for (DateFilter filter : DateFilter.values()) {
			filters.put(filter, filter.value);
		}
		return filters;
	}

	private void addRequestsToModel(Model model, UUID uuid, DateFilter filter) {
		Map<String, Long> requests = analythicsService.getRequestsByApplicationUuid(uuid, filter);
		model.addAttribute("requests", requests);
	}

	private void addApplicationToModel(Model model, UUID uuid) {
		Application app = applicationService.getApplicationByUuid(uuid);
		model.addAttribute("app", app);
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
