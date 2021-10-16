package me.mikolaj.messageboard.response;

import me.mikolaj.messageboard.post.PostRepository;
import me.mikolaj.messageboard.post.exception.PostNotFoundException;
import me.mikolaj.messageboard.user.UserRepository;
import me.mikolaj.messageboard.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ResponseMapper {

	private final UserRepository userRepository;
	private final PostRepository postRepository;

	public ResponseMapper(final UserRepository userRepository, final PostRepository postRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	public ResponseDto toDto(final Response response) {
		final ResponseDto responseDto = new ResponseDto();
		responseDto.setId(response.getId());
		responseDto.setContent(response.getContent());
		responseDto.setAddedDate(response.getAddedDate());
		responseDto.setPostId(response.getPost().getId());
		responseDto.setAuthorUsername(response.getAuthor().getUsername());

		return responseDto;
	}

	public Response toEntity(final ResponseDto responseDto) {
		final Response response = new Response();
		response.setId(responseDto.getId());
		response.setContent(responseDto.getContent());
		response.setAddedDate(responseDto.getAddedDate());
		response.setAuthor(userRepository
				.findByUsername(responseDto.getAuthorUsername())
				.orElseThrow(UserNotFoundException::new));
		response.setPost(postRepository
				.findById(responseDto.getPostId())
				.orElseThrow(PostNotFoundException::new));

		return response;
	}
}
