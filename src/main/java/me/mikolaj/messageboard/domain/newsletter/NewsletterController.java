package me.mikolaj.messageboard.domain.newsletter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewsletterController {

	private final NewsletterService newsletterService;

	public NewsletterController(final NewsletterService newsletterService) {
		this.newsletterService = newsletterService;
	}

	@PostMapping("/newsletter")
	public String addEmailToNewsletter(@RequestParam final String email) {
		newsletterService.addEmailToNewsletter(email);
		return "redirect:/";
	}
}
