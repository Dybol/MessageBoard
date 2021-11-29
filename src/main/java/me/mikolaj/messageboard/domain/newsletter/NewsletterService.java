package me.mikolaj.messageboard.domain.newsletter;

import me.mikolaj.messageboard.config.email.EmailProducer;
import me.mikolaj.messageboard.config.email.EmailSender;
import me.mikolaj.messageboard.config.email.EmailValidator;
import me.mikolaj.messageboard.config.security.registration.exception.EmailNotValidateException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsletterService {

	private final NewsletterRepository newsletterRepository;
	private final NewsletterMapper newsletterMapper;
	private final EmailValidator emailValidator;
	private final EmailSender emailSender;

	public NewsletterService(final NewsletterRepository newsletterRepository,
							 final NewsletterMapper newsletterMapper, final EmailValidator emailValidator,
							 @EmailProducer(type = EmailProducer.ProducerType.NEWSLETTER) final EmailSender emailSender) {
		this.newsletterRepository = newsletterRepository;
		this.newsletterMapper = newsletterMapper;
		this.emailValidator = emailValidator;
		this.emailSender = emailSender;
	}

	public List<NewsletterDto> findAllNewsletterObj() {
		return newsletterRepository
				.findAll()
				.stream()
				.map(newsletterMapper::toDto)
				.collect(Collectors.toList());
	}

	public List<String> findAllEmails() {
		return findAllNewsletterObj()
				.stream()
				.map(NewsletterDto::getEmail)
				.collect(Collectors.toList());
	}

	public void addEmailToNewsletter(final String email) {
		if (!emailValidator.test(email))
			throw new EmailNotValidateException();

		final NewsletterDto newsletterDto = new NewsletterDto();
		newsletterDto.setJoinedAt(LocalDateTime.now());
		newsletterDto.setEmail(email);
		newsletterRepository.save(newsletterMapper.toEntity(newsletterDto));
	}

	public void sendNewsletter(final String message) {
		final List<String> allEmails = findAllEmails();
		allEmails.forEach(to -> emailSender.send(to, message));
	}
}
