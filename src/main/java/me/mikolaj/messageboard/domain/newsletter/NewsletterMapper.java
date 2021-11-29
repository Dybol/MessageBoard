package me.mikolaj.messageboard.domain.newsletter;

import org.springframework.stereotype.Service;

@Service
public class NewsletterMapper {

	public NewsletterDto toDto(final Newsletter newsletter) {
		final NewsletterDto newsletterDto = new NewsletterDto();
		newsletterDto.setId(newsletter.getId());
		newsletterDto.setEmail(newsletter.getEmail());
		newsletterDto.setJoinedAt(newsletter.getJoinedAt());
		return newsletterDto;
	}

	public Newsletter toEntity(final NewsletterDto newsletterDto) {
		final Newsletter newsletter = new Newsletter();
		newsletter.setId(newsletterDto.getId());
		newsletter.setEmail(newsletterDto.getEmail());
		newsletter.setJoinedAt(newsletterDto.getJoinedAt());
		return newsletter;
	}
}
