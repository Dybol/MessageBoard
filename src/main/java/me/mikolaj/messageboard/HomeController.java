package me.mikolaj.messageboard;

import me.mikolaj.messageboard.chat.ChatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class HomeController {

	private final ChatService chatService;

	public HomeController(final ChatService chatService) {
		this.chatService = chatService;
	}

	@GetMapping("/")
	public String getHome(final Model model) {
		model.addAttribute("messages", chatService.findLastMessages());
		return "home";
	}

	@PostMapping("/")
	public String addMessage(@RequestParam final String message, final Principal principal) {
		chatService.addMessage(message, principal);
		return "redirect:/";
	}
}
