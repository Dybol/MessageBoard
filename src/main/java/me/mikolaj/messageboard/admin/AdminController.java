package me.mikolaj.messageboard.admin;

import me.mikolaj.messageboard.newsletter.NewsletterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final NewsletterService newsletterService;

	public AdminController(final NewsletterService newsletterService) {
		this.newsletterService = newsletterService;
	}

	@GetMapping
	public String getAdminHome() {
		return "admin/home";
	}

	@GetMapping("/newsletter")
	public String getNewsletter() {
		return "admin/newsletter";
	}

	@PostMapping("/newsletter")
	public String addNewsletter(@RequestParam final String message) {
		newsletterService.sendNewsletter(message);
		return "redirect:/admin/newsletter";
	}

}
