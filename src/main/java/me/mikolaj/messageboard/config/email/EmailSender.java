package me.mikolaj.messageboard.config.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public abstract class EmailSender {

	private final static Logger LOGGER = LoggerFactory.getLogger(EmailNewsletter.class);

	private final JavaMailSender mailSender;

	public EmailSender(final JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Async
	public void send(final String to, final String email) {
		try {
			final MimeMessage mimeMessage = mailSender.createMimeMessage();
			final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setText(email, true);
			helper.setTo(to);
			setProperties(helper);
			mailSender.send(mimeMessage);

		} catch (final MessagingException exception) {
			LOGGER.error("Nie udało sie wysłać maila", exception);
			throw new IllegalStateException("Nie udało sie wysłać maila");
		}
	}

	public abstract void setProperties(MimeMessageHelper helper) throws MessagingException;
}
