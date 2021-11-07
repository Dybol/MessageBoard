package me.mikolaj.messageboard.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@EmailProducer(type = EmailProducer.ProducerType.REGISTRATION)
public class EmailRegistration implements EmailSender {

	private final static Logger LOGGER = LoggerFactory.getLogger(EmailRegistration.class);

	private final JavaMailSender mailSender;

	public EmailRegistration(final JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void send(final String to, final String email) {
		try {
			final MimeMessage mimeMessage = mailSender.createMimeMessage();
			final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setText(email, true);
			helper.setTo(to);
			helper.setSubject("Potwierdź swój email");
			helper.setFrom("hello@messageboard.pl");
			mailSender.send(mimeMessage);

		} catch (final MessagingException exception) {
			LOGGER.error("Nie udało sie wysłać maila", exception);
			throw new IllegalStateException("Nie udało sie wysłać maila");
		}
	}
}
