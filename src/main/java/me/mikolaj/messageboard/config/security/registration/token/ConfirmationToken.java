package me.mikolaj.messageboard.config.security.registration.token;

import me.mikolaj.messageboard.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ConfirmationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String token;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private LocalDateTime expiresAt;
	private LocalDateTime confirmedAt;

	@ManyToOne
	@JoinColumn(
			nullable = false,
			name = "user_id"
	)
	private User user;

	public ConfirmationToken(final String token, final LocalDateTime createdAt, final LocalDateTime expiresAt, final User user) {
		this.token = token;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
		this.user = user;
	}

	public ConfirmationToken() {
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(final String token) {
		this.token = token;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(final LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(final LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}

	public LocalDateTime getConfirmedAt() {
		return confirmedAt;
	}

	public void setConfirmedAt(final LocalDateTime confirmedAt) {
		this.confirmedAt = confirmedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ConfirmationToken{" +
				"id=" + id +
				", token='" + token + '\'' +
				", createdAt=" + createdAt +
				", expiredAt=" + expiresAt +
				", confirmedAt=" + confirmedAt +
				'}';
	}
}
