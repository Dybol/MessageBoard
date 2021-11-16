package me.mikolaj.messageboard.user;

import me.mikolaj.messageboard.post.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {

	private final PostService postService;

	public UserController(final PostService postService) {
		this.postService = postService;
	}

	@GetMapping("/moje-posty")
	public String getUserPosts(final Model model, final Principal principal) {
		model.addAttribute("posts", postService.findAllByUserId(principal));

		return "user/posts";
	}
}
