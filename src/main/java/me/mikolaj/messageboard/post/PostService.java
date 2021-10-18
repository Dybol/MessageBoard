package me.mikolaj.messageboard.post;

import me.mikolaj.messageboard.response.ResponseDto;
import me.mikolaj.messageboard.response.ResponseMapper;
import me.mikolaj.messageboard.response.ResponseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

	private final PostRepository postRepository;
	private final ResponseRepository responseRepository;
	private final PostMapper postMapper;
	private final ResponseMapper responseMapper;

	public PostService(final PostRepository postRepository, final ResponseRepository responseRepository, final PostMapper postMapper, final ResponseMapper responseMapper) {
		this.postRepository = postRepository;
		this.responseRepository = responseRepository;
		this.postMapper = postMapper;
		this.responseMapper = responseMapper;
	}

	public List<PostDto> findAll() {
		return postRepository.findAll()
				.stream().map(postMapper::toDto)
				.collect(Collectors.toList());
	}

	public Optional<PostDto> findById(final Long id) {
		return postRepository.findById(id).map(postMapper::toDto);
	}

	public List<ResponseDto> findAllResponses(final Long postId) {
		return responseRepository.findAllByPostId(postId)
				.stream().map(responseMapper::toDto)
				.collect(Collectors.toList());
	}
}
