package me.mikolaj.messageboard.domain.post;

import me.mikolaj.messageboard.domain.response.Response;
import me.mikolaj.messageboard.user.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(length = 100)
	private String subject;

	@NotNull
	private String content;

	private String category;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime addedDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User author;

	@OneToMany(mappedBy = "post")
	private List<Response> responses;

	public Post() {
	}

	public Post(final String subject, final String content, final String category, final LocalDateTime addedDate) {
		this.subject = subject;
		this.content = content;
		this.category = category;
		this.addedDate = addedDate;
	}

	public Long getId() {
		return id;
	}

	public String getSubject() {
		return subject;
	}

	public String getContent() {
		return content;
	}

	public String getCategory() {
		return category;
	}

	public LocalDateTime getAddedDate() {
		return addedDate;
	}

	public User getAuthor() {
		return author;
	}

	public List<Response> getResponses() {
		return responses;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public void setCategory(final String category) {
		this.category = category;
	}

	public void setAddedDate(final LocalDateTime localDateTime) {
		this.addedDate = localDateTime;
	}

	public void setAuthor(final User author) {
		this.author = author;
	}

	public void setResponses(final List<Response> responses) {
		this.responses = responses;
	}
}
