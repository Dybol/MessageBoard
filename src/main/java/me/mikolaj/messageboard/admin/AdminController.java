package me.mikolaj.messageboard.admin;

import me.mikolaj.messageboard.domain.newsletter.NewsletterService;
import me.mikolaj.messageboard.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final NewsletterService newsletterService;
	private final AdminService adminService;
	private final UserService userService;

	public AdminController(final NewsletterService newsletterService, final AdminService adminService, final UserService userService) {
		this.newsletterService = newsletterService;
		this.adminService = adminService;
		this.userService = userService;
	}

	@GetMapping
	public String getAdminHome(final Model model) {
		model.addAttribute("postsCount", adminService.countAllPosts());
		model.addAttribute("responsesCount", adminService.countAllResponses());
		model.addAttribute("usersCount", adminService.countAllUsers());
		model.addAttribute("newUsersCount", adminService.countNewUsers());
		model.addAttribute("newUsers", userService.findAllNewUsers());
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
