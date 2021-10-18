package me.mikolaj.messageboard.post;

import me.mikolaj.messageboard.response.Response;
import me.mikolaj.messageboard.response.ResponseRepository;
import me.mikolaj.messageboard.response.exception.ResponseNotFoundException;
import me.mikolaj.messageboard.user.UserRepository;
import me.mikolaj.messageboard.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PostMapper {

	private final UserRepository userRepository;
	private final ResponseRepository responseRepository;

	public PostMapper(final UserRepository userRepository, final ResponseRepository responseRepository) {
		this.userRepository = userRepository;
		this.responseRepository = responseRepository;
	}

	public PostDto toDto(final Post post) {
		final PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		postDto.setSubject(post.getSubject());
		postDto.setContent(post.getContent());
		postDto.setShortContent(createShortContent(post.getContent()));
		//postDto.setShortContent("XD");
		postDto.setCategory(post.getCategory());
		postDto.setAddedDate(post.getAddedDate());
		postDto.setAuthorUsername(post.getAuthor().getUsername());
		postDto.setResponsesIds(post.getResponses().stream().map(Response::getId).collect(Collectors.toList()));

		return postDto;
	}

	public Post toEntity(final PostDto postDto) {
		final Post post = new Post();
		post.setId(postDto.getId());
		post.setSubject(postDto.getSubject());
		post.setContent(postDto.getContent());
		post.setCategory(postDto.getCategory());
		post.setAddedDate(postDto.getAddedDate());
		post.setAuthor(userRepository
				.findByUsername(postDto.getAuthorUsername())
				.orElseThrow(UserNotFoundException::new));
		post.setResponses(postDto.getResponsesIds()
				.stream().map(responseRepository::findById)
				.map(response -> response.orElseThrow(ResponseNotFoundException::new))
				.collect(Collectors.toList()));

		return post;
	}

	private String createShortContent(final String content) {
		final StringBuilder shortContent = new StringBuilder();
		if (content.split(" ").length <= 10)
			return content;
		else {
			int counter = 0;
			for (final String word : content.split(" ")) {
				shortContent.append(word);

				if (counter == 10) {
					shortContent.append("...");
					break;
				}
				shortContent.append(" ");

				counter++;
			}
			return shortContent.toString();
		}
	}
}
