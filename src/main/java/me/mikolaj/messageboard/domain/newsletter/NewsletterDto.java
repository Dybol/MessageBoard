package me.mikolaj.messageboard.domain.newsletter;

import java.time.LocalDateTime;
import java.util.Objects;

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

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final NewsletterDto that = (NewsletterDto) o;
		return Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(joinedAt, that.joinedAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, email, joinedAt);
	}
}
