package me.mikolaj.messageboard.admin;

import me.mikolaj.messageboard.post.PostService;
import me.mikolaj.messageboard.response.ResponseService;
import me.mikolaj.messageboard.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	private final PostService postService;
	private final ResponseService responseService;
	private final UserService userService;

	public AdminService(final PostService postService, final ResponseService responseService, final UserService userService) {
		this.postService = postService;
		this.responseService = responseService;
		this.userService = userService;
	}

	public Integer countAllPosts() {
		return postService.findAll().size();
	}

	public Integer countAllResponses() {
		return responseService.findAll().size();
	}

	public Integer countAllUsers() {
		return userService.findAll().size();
	}

	public Integer countNewUsers() {
		return userService.findAllNewUsers().size();
	}
}
