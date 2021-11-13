package me.mikolaj.messageboard.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;

@Service
@EmailProducer(type = EmailProducer.ProducerType.NEWSLETTER)
public class EmailNewsletter implements EmailSender {

	private final static Logger LOGGER = LoggerFactory.getLogger(EmailNewsletter.class);

	private final JavaMailSender mailSender;

	public EmailNewsletter(final JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	@Async
	public void send(final String to, final String email) {
		try {
			final MimeMessage mimeMessage = mailSender.createMimeMessage();
			final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setText(email, false);
			helper.setTo(to);
			helper.setSubject("Messageboard - Newsletter " + LocalDateTime.now().getMonth() + " / " + LocalDateTime.now().getYear());
			helper.setFrom("newsletter@messageboard.pl");
			mailSender.send(mimeMessage);

		} catch (final MessagingException exception) {
			LOGGER.error("Nie udało sie wysłać maila", exception);
			throw new IllegalStateException("Nie udało sie wysłać maila");
		}
	}
}
