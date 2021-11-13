package me.mikolaj.messageboard.newsletter;

import java.time.LocalDateTime;

public class NewsletterDto {

	private Long id;
	private String email;

	private LocalDateTime joinedAt;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public LocalDateTime getJoinedAt() {
		return joinedAt;
	}

	public void setJoinedAt(final LocalDateTime joinedAt) {
		this.joinedAt = joinedAt;
	}
}
