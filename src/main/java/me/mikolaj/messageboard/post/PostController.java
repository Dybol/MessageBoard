package me.mikolaj.messageboard.post;

import me.mikolaj.messageboard.post.exception.PostNotFoundException;
import me.mikolaj.messageboard.response.ResponseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@RequestMapping("/posty")
@Controller
public class PostController {

	private final PostService postService;
	private final ResponseService responseService;

	public PostController(final PostService postService, final ResponseService responseService) {
		this.postService = postService;
		this.responseService = responseService;
	}

	@GetMapping
	public String getPosts() {
		return "/home";
	}

	@GetMapping("/polityka")
	public String getPolitics(final Model model) {
		model.addAttribute("posts", postService.findAllOrderByDateDesc());
		return "posts/politics";
	}

	@PostMapping("/polityka")
	public String addPoliticPost(@RequestParam final String title, @RequestParam final String message, final Principal principal) {
		postService.addPost(title, message, principal.getName(), "polityka");

		return "redirect:/posty/polityka";
	}

	@GetMapping("/polityka/post")
	public String getPost(@RequestParam final Long id, final Model model) {
		model.addAttribute("post", postService.findById(id).orElseThrow(PostNotFoundException::new));
		model.addAttribute("responses", postService.findAllResponses(id));
		return "posts/post";
	}

	@PostMapping("/polityka/post")
	public String addResponse(@RequestParam final String message, @RequestParam final Long postId, final Principal principal) {
		responseService.addResponse(message, postId, principal.getName());
		System.out.println(principal.getName());
		System.out.println("Post mess" + message);
		System.out.println("id post" + postId);
		return "redirect:/posty/polityka/post?id=" + postId;
	}
}
