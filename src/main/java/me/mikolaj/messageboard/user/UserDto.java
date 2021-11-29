package me.mikolaj.messageboard.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDto {

	private Long id;

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String username;
	private LocalDateTime joinedAt;
	private boolean enabled;
	private boolean locked;
	private List<Long> responsesIds = new ArrayList<>();
	private List<Long> postsIds = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public LocalDateTime getJoinedAt() {
		return joinedAt;
	}

	public void setJoinedAt(final LocalDateTime joinedAt) {
		this.joinedAt = joinedAt;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(final boolean locked) {
		this.locked = locked;
	}

	public List<Long> getResponsesIds() {
		return responsesIds;
	}

	public void setResponsesIds(final List<Long> responsesIds) {
		this.responsesIds = responsesIds;
	}

	public List<Long> getPostsIds() {
		return postsIds;
	}

	public void setPostsIds(final List<Long> postsIds) {
		this.postsIds = postsIds;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final UserDto userDto = (UserDto) o;
		return enabled == userDto.enabled && locked == userDto.locked && Objects.equals(id, userDto.id)
				&& Objects.equals(firstName, userDto.firstName) && Objects.equals(lastName, userDto.lastName)
				&& Objects.equals(email, userDto.email) && Objects.equals(password, userDto.password)
				&& Objects.equals(username, userDto.username) && Objects.equals(joinedAt, userDto.joinedAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, lastName, email, password, username, joinedAt, enabled, locked);
	}
}
