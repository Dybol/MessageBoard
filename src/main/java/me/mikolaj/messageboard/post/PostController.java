package me.mikolaj.messageboard.post;

import me.mikolaj.messageboard.post.exception.PostNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/posty")
@Controller
public class PostController {

	private final PostService postService;

	public PostController(final PostService postService) {
		this.postService = postService;
	}

	@GetMapping
	public String getPosts() {
		return "/home";
	}

	@GetMapping("/polityka")
	public String getPolitics(@RequestParam(required = false) final Long id, final Model model) {
		if (id == null) {
			model.addAttribute("posts", postService.findAll());
			return "posts/politics";
		} else {
			model.addAttribute("post", postService.findById(id).orElseThrow(PostNotFoundException::new));
			model.addAttribute("responses", postService.findAllResponses(id));
			return "posts/post";
		}
	}
}
