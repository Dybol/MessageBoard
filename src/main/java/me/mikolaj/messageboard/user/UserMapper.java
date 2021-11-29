package me.mikolaj.messageboard.user;

import me.mikolaj.messageboard.domain.post.Post;
import me.mikolaj.messageboard.domain.post.PostRepository;
import me.mikolaj.messageboard.domain.post.exception.PostNotFoundException;
import me.mikolaj.messageboard.domain.response.Response;
import me.mikolaj.messageboard.domain.response.ResponseRepository;
import me.mikolaj.messageboard.domain.response.exception.ResponseNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserMapper {

	private final ResponseRepository responseRepository;
	private final PostRepository postRepository;

	public UserMapper(final ResponseRepository responseRepository, final PostRepository postRepository) {
		this.responseRepository = responseRepository;
		this.postRepository = postRepository;
	}

	public UserDto toDto(final User user) {
		final UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setUsername(user.getUsername());
		userDto.setJoinedAt(user.getJoinedAt());
		userDto.setEnabled(user.isEnabled());
		userDto.setLocked(user.isLocked());
		userDto.setResponsesIds(user.getResponses()
				.stream().map(Response::getId)
				.collect(Collectors.toList()));
		userDto.setPostsIds(user.getPosts()
				.stream().map(Post::getId)
				.collect(Collectors.toList()));

		return userDto;
	}

	public User toEntity(final UserDto userDto) {
		final User user = new User();
		user.setId(userDto.getId());
		user.setLastName(userDto.getLastName());
		user.setFirstName(userDto.getFirstName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setUsername(userDto.getUsername());
		user.setJoinedAt(userDto.getJoinedAt());
		user.setLocked(userDto.isLocked());
		user.setEnabled(userDto.isEnabled());
		user.setResponses(userDto.getResponsesIds()
				.stream().map(responseRepository::findById)
				.map(response -> response.orElseThrow(ResponseNotFoundException::new))
				.collect(Collectors.toList()));
		user.setPosts(userDto.getPostsIds()
				.stream().map(postRepository::findById)
				.map(post -> post.orElseThrow(PostNotFoundException::new))
				.collect(Collectors.toList()));

		return user;
	}
}
