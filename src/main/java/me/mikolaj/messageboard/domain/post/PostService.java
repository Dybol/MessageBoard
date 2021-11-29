package me.mikolaj.messageboard.domain.post;

import me.mikolaj.messageboard.domain.response.ResponseDto;
import me.mikolaj.messageboard.domain.response.ResponseMapper;
import me.mikolaj.messageboard.domain.response.ResponseRepository;
import me.mikolaj.messageboard.user.UserDto;
import me.mikolaj.messageboard.user.UserService;
import me.mikolaj.messageboard.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

	private final PostRepository postRepository;
	private final ResponseRepository responseRepository;
	private final PostMapper postMapper;
	private final ResponseMapper responseMapper;
	private final UserService userService;

	public PostService(final PostRepository postRepository, final ResponseRepository responseRepository,
					   final PostMapper postMapper, final ResponseMapper responseMapper, final UserService userService) {
		this.postRepository = postRepository;
		this.responseRepository = responseRepository;
		this.postMapper = postMapper;
		this.responseMapper = responseMapper;
		this.userService = userService;
	}

	public List<PostDto> findAll() {
		return postRepository.findAll()
				.stream().map(postMapper::toDto)
				.collect(Collectors.toList());
	}

	public List<PostDto> findAllOrderByDateDesc() {
		return postRepository
				.findAllOrderByAddedDateDsc()
				.stream().map(postMapper::toDto)
				.collect(Collectors.toList());
	}

	public List<PostDto> findAllByCategoryOrdered(final String category) {
		return postRepository
				.findAllByCategoryOrdered(category)
				.stream().map(postMapper::toDto)
				.collect(Collectors.toList());
	}

	public List<PostDto> findAllByUserId(final Long id) {
		return postRepository.findAllByUserId(id)
				.stream().map(postMapper::toDto)
				.collect(Collectors.toList());
	}

	public List<PostDto> findAllByUserId(final Principal principal) {
		final UserDto userDto = userService.findByEmail(principal.getName()).orElseThrow(UserNotFoundException::new);
		return findAllByUserId(userDto.getId());
	}

	public Optional<PostDto> findById(final Long id) {
		return postRepository.findById(id).map(postMapper::toDto);
	}

	public List<ResponseDto> findAllResponses(final Long postId) {
		return responseRepository.findAllByPostId(postId)
				.stream().map(responseMapper::toDto)
				.collect(Collectors.toList());
	}

	public void addPost(final String title, final String message, final String userEmail, final String category) {
		final UserDto userDto = userService.findByEmail(userEmail).orElseThrow(UserNotFoundException::new);
		final PostDto post = new PostDto();
		post.setSubject(title);
		post.setContent(message);
		post.setAddedDate(LocalDateTime.now());
		post.setAuthorUsername(userDto.getUsername());
		post.setCategory(category);
		postRepository.save(postMapper.toEntity(post));
	}
}
