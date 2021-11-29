package me.mikolaj.messageboard.domain.chat;

import org.springframework.stereotype.Service;

@Service
public class ChatMessageMapper {

	public ChatMessageDto toDto(final ChatMessage chatMessage) {
		final ChatMessageDto chatMessageDto = new ChatMessageDto();
		chatMessageDto.setId(chatMessage.getId());
		chatMessageDto.setAuthorUsername(chatMessage.getAuthorUsername());
		chatMessageDto.setSentAt(chatMessage.getSentAt());
		chatMessageDto.setMessage(chatMessage.getMessage());

		return chatMessageDto;
	}

	public ChatMessage toEntity(final ChatMessageDto chatMessageDto) {
		final ChatMessage chatMessage = new ChatMessage();
		if (chatMessageDto.getId() != null)
			chatMessage.setId(chatMessageDto.getId());
		chatMessage.setSentAt(chatMessageDto.getSentAt());
		chatMessage.setAuthorUsername(chatMessageDto.getAuthorUsername());
		chatMessage.setMessage(chatMessageDto.getMessage());

		return chatMessage;
	}

}
