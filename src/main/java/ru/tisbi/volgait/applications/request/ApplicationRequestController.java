package ru.tisbi.volgait.applications.request;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationRequestController {

	private final ApplicationRequestService requestService;

	public ApplicationRequestController(ApplicationRequestService requestService) {
		this.requestService = requestService;
	}

	@PostMapping("/request")
	public void addRequest(@RequestParam UUID uuid, @RequestParam String title,
			@RequestParam String additionalInformation) {
		requestService.addRequest(uuid, title, additionalInformation);
	}

}
