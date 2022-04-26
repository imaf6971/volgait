package ru.tisbi.volgait.main.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.tisbi.volgait.applications.ApplicationService;

@Slf4j
@Controller
public class IndexController {

    private final ApplicationService applicationService;

    public IndexController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/")
    public String index(Model model, Authentication authentication) {
        if (authentication != null) {
            log.info("lol");
            String email = authentication.getName();
            model.addAttribute("apps_counter", applicationService.countUserApplications(email));
        }
        return "index";
    }

}
