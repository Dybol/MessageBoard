package me.mikolaj.messageboard.domain.post;

import java.time.LocalDateTime;
import java.util.List;

public class PostDto {

	private Long id;

	private String subject;
	private String content;
	private String category;
	private LocalDateTime addedDate;

	private String shortContent;

	private String authorUsername;

	private List<Long> responsesIds;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public String getShortContent() {
		return shortContent;
	}

	public void setShortContent(final String shortContent) {
		this.shortContent = shortContent;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(final String category) {
		this.category = category;
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

	public List<Long> getResponsesIds() {
		return responsesIds;
	}

	public void setResponsesIds(final List<Long> responsesIds) {
		this.responsesIds = responsesIds;
	}
}
