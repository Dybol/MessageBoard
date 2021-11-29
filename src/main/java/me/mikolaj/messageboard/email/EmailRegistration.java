package me.mikolaj.messageboard.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@EmailProducer(type = EmailProducer.ProducerType.REGISTRATION)
public class EmailRegistration extends EmailSender {

	public EmailRegistration(final JavaMailSender mailSender) {
		super(mailSender);
	}

	@Override
	public void setProperties(final MimeMessageHelper helper) throws MessagingException {
		helper.setSubject("Potwierdź swój email");
		helper.setFrom("hello@messageboard.pl");
	}
}
