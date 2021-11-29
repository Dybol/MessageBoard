package me.mikolaj.messageboard.domain.response;

import me.mikolaj.messageboard.user.UserDto;
import me.mikolaj.messageboard.user.UserService;
import me.mikolaj.messageboard.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseService {

	private final ResponseRepository responseRepository;
	private final ResponseMapper responseMapper;
	private final UserService userService;

	public ResponseService(final ResponseRepository responseRepository, final ResponseMapper responseMapper, final UserService userService) {
		this.responseRepository = responseRepository;
		this.responseMapper = responseMapper;
		this.userService = userService;
	}

	public void addResponse(final String message, final Long postId, final String userEmail) {
		final UserDto userDto = userService.findByEmail(userEmail).orElseThrow(UserNotFoundException::new);
		final ResponseDto responseDto = new ResponseDto();
		responseDto.setAddedDate(LocalDateTime.now());
		responseDto.setContent(message);
		responseDto.setAuthorUsername(userDto.getUsername());
		responseDto.setPostId(postId);
		responseRepository.save(responseMapper.toEntity(responseDto));
	}

	public List<ResponseDto> findAll() {
		return responseRepository.findAll()
				.stream().map(responseMapper::toDto)
				.collect(Collectors.toList());
	}
}
