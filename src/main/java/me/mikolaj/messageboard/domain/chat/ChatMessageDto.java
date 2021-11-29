package me.mikolaj.messageboard.domain.chat;

import java.time.LocalDateTime;
import java.util.Objects;

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

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final ChatMessageDto that = (ChatMessageDto) o;
		return Objects.equals(id, that.id) && Objects.equals(authorUsername, that.authorUsername)
				&& Objects.equals(message, that.message) && Objects.equals(sentAt, that.sentAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, authorUsername, message, sentAt);
	}
}
