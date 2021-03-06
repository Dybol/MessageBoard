package me.mikolaj.messageboard.domain.newsletter;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Newsletter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Email
	private String email;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime joinedAt;

	public Newsletter() {
	}

	public Newsletter(final String email, final LocalDateTime joinedAt) {
		this.email = email;
		this.joinedAt = joinedAt;
	}

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
		final Newsletter that = (Newsletter) o;
		return Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(joinedAt, that.joinedAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, email, joinedAt);
	}
}
