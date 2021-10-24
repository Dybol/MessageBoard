package me.mikolaj.messageboard.chat;

import java.time.LocalDateTime;

public class ChatMessageDto {

	private Long id;
	private String authorUsername;
	private String message;
	private LocalDateTime sentAt;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getAuthorUsername() {
		return authorUsername;
	}

	public void setAuthorUsername(final String authorUsername) {
		this.authorUsername = authorUsername;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public LocalDateTime getSentAt() {
		return sentAt;
	}

	public void setSentAt(final LocalDateTime sentAt) {
		this.sentAt = sentAt;
	}
}
