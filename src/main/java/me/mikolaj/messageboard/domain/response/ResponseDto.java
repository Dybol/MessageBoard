package me.mikolaj.messageboard.domain.response;

import java.time.LocalDateTime;
import java.util.Objects;

public class ResponseDto {

	private Long id;

	private String content;
	private LocalDateTime addedDate;

	private String authorUsername;

	private String authorEmail;

	private Long postId;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public LocalDateTime getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(final LocalDateTime addedDate) {
		this.addedDate = addedDate;
	}

	public String getAuthorUsername() {
		return authorUsername;
	}

	public void setAuthorUsername(final String authorUsername) {
		this.authorUsername = authorUsername;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}

	public void setAuthorEmail(final String authorEmail) {
		this.authorEmail = authorEmail;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(final Long postId) {
		this.postId = postId;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final ResponseDto that = (ResponseDto) o;
		return Objects.equals(id, that.id) && Objects.equals(content, that.content)
				&& Objects.equals(addedDate, that.addedDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, content, addedDate);
	}
}
