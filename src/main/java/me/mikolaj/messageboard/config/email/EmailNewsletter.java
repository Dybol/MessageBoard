package me.mikolaj.messageboard.config.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;

@Service
@EmailProducer(type = EmailProducer.ProducerType.NEWSLETTER)
public class EmailNewsletter extends EmailSender {

	public EmailNewsletter(final JavaMailSender mailSender) {
		super(mailSender);
	}

	@Override
	public void setProperties(final MimeMessageHelper helper) throws MessagingException {
		helper.setSubject("Messageboard - Newsletter " + LocalDateTime.now().getMonth() + " / " + LocalDateTime.now().getYear());
		helper.setFrom("newsletter@messageboard.pl");
	}
}
