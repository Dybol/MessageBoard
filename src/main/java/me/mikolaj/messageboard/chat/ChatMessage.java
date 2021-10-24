package me.mikolaj.messageboard.chat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class ChatMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String authorUsername;

	private String message;

	private LocalDateTime sentAt;

	public ChatMessage() {
	}

	public ChatMessage(final String authorUsername, final String message, final LocalDateTime sentAt) {
		this.authorUsername = authorUsername;
		this.message = message;
		this.sentAt = sentAt;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
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
