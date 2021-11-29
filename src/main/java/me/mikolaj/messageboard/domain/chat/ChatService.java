package me.mikolaj.messageboard.domain.chat;

import me.mikolaj.messageboard.user.UserDto;
import me.mikolaj.messageboard.user.UserService;
import me.mikolaj.messageboard.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

	private final ChatRepository chatRepository;
	private final ChatMessageMapper chatMessageMapper;
	private final UserService userService;

	public ChatService(final ChatRepository chatRepository, final ChatMessageMapper chatMessageMapper, final UserService userService) {
		this.chatRepository = chatRepository;
		this.chatMessageMapper = chatMessageMapper;
		this.userService = userService;
	}

	public List<ChatMessageDto> findAll() {
		return chatRepository.findAll()
				.stream().map(chatMessageMapper::toDto)
				.collect(Collectors.toList());
	}

	public List<ChatMessageDto> findLastMessages() {
		return chatRepository.find20LastMessages()
				.stream().map(chatMessageMapper::toDto)
				.collect(Collectors.toList());
	}

	public void addMessage(final String message, final Principal principal) {
		final ChatMessageDto messageDto = new ChatMessageDto();
		final UserDto userDto = userService.findByEmail(principal.getName()).orElseThrow(UserNotFoundException::new);
		messageDto.setMessage(message);
		messageDto.setSentAt(LocalDateTime.now());
		messageDto.setAuthorUsername(userDto.getUsername());
		chatRepository.save(chatMessageMapper.toEntity(messageDto));
	}
}
