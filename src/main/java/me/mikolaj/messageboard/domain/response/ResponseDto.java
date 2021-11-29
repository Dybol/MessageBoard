package me.mikolaj.messageboard.domain.response;

import java.time.LocalDateTime;

public class ResponseDto {

	private Long id;

	private String content;
	private LocalDateTime addedDate;

	private String authorUsername;

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

	public Long getPostId() {
		return postId;
	}

	public void setPostId(final Long postId) {
		this.postId = postId;
	}
}
