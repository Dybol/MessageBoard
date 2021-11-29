package me.mikolaj.messageboard.domain.response;

import me.mikolaj.messageboard.domain.post.Post;
import me.mikolaj.messageboard.user.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Response {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String content;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime addedDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User author;

	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;

	public Response() {
	}

	public Response(final String content, final LocalDateTime addedDate, final User author, final Post post) {
		this.content = content;
		this.addedDate = addedDate;
		this.author = author;
		this.post = post;
	}

	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public LocalDateTime getAddedDate() {
		return addedDate;
	}

	public User getAuthor() {
		return author;
	}

	public Post getPost() {
		return post;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public void setAddedDate(final LocalDateTime addedDate) {
		this.addedDate = addedDate;
	}

	public void setAuthor(final User author) {
		this.author = author;
	}

	public void setPost(final Post post) {
		this.post = post;
	}
}
