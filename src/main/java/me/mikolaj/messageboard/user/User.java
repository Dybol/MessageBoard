package me.mikolaj.messageboard.user;

import me.mikolaj.messageboard.post.Post;
import me.mikolaj.messageboard.response.Response;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@Column(length = 30, nullable = false)
	@NotNull
	private String firstName;

	@Column(length = 30)
	@NotNull
	private String lastName;

	@Column(unique = true, length = 60)
	@NotNull
	@Email
	private String email;

	@Column(length = 100)
	@NotNull
	private String password;

	@Column(unique = true, length = 30)
	private String username;

	private LocalDateTime joinedAt;

	private boolean enabled = false;

	private boolean locked = false;

	@OneToMany(mappedBy = "author")
	private final List<Response> responses = new ArrayList<>();

	@OneToMany(mappedBy = "author")
	private final List<Post> posts = new ArrayList<>();

	public User() {
	}

	public User(final String firstName, final String lastName, final String email, final String password, final String username, final LocalDateTime joinedAt) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.username = username;
		this.joinedAt = joinedAt;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public LocalDateTime getJoinedAt() {
		return joinedAt;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public void setJoinedAt(final LocalDateTime joinedAt) {
		this.joinedAt = joinedAt;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	public void setLocked(final boolean locked) {
		this.locked = locked;
	}
}
