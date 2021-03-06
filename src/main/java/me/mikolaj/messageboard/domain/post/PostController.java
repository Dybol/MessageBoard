package me.mikolaj.messageboard.domain.post;

import com.sun.xml.internal.ws.util.StringUtils;
import me.mikolaj.messageboard.domain.post.exception.PostNotFoundException;
import me.mikolaj.messageboard.domain.response.ResponseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/{category}")
	public String getPolitics(final Model model, @PathVariable final String category) {
		model.addAttribute("category", StringUtils.capitalize(category));
		model.addAttribute("posts", postService.findAllByCategoryOrdered(category));
		return "posts/posts";
	}

	@PostMapping("/{category}")
	public String addPoliticPost(@RequestParam final String title, @RequestParam final String message,
								 final Principal principal, @PathVariable final String category) {
		postService.addPost(title, message, principal.getName(), category.toLowerCase());

		return "redirect:/posty/" + category.toLowerCase();
	}

	@GetMapping("/{category}/post")
	public String getPost(@RequestParam final Long id, final Model model, @PathVariable final String category) {
		model.addAttribute("post", postService.findById(id).orElseThrow(PostNotFoundException::new));
		model.addAttribute("responses", postService.findAllResponses(id));
		model.addAttribute("authorEmail", postService.findAuthorEmail(id));
		return "posts/post";
	}

	@PostMapping("/{category}/post")
	public String addResponse(@RequestParam final String message, @RequestParam final Long postId, final Principal principal, @PathVariable final String category) {
		responseService.addResponse(message, postId, principal.getName());
		return "redirect:/posty/" + category + "/post?id=" + postId;
	}

	@GetMapping("/{category}/post/usun/{id}")
	public String deletePost(@PathVariable final String category, @PathVariable final String id, final Principal principal) {
		postService.delete(id, principal);
		return "redirect:/posty/" + category;
	}

	@GetMapping("/{category}/post/{postId}/usun-odpowiedz/{responseId}")
	public String deleteResponse(@PathVariable final String category, @PathVariable final String postId, @PathVariable final String responseId, final Principal principal) {
		responseService.delete(responseId, principal);
		return "redirect:/posty/" + category + "/post?id=" + postId;
	}
}
