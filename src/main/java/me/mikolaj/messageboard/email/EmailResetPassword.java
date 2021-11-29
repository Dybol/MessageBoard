package me.mikolaj.messageboard.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@EmailProducer(type = EmailProducer.ProducerType.PASSWORD)
public class EmailResetPassword extends EmailSender {

	public EmailResetPassword(final JavaMailSender mailSender) {
		super(mailSender);
	}

	@Override
	public void setProperties(final MimeMessageHelper helper) throws MessagingException {
		helper.setSubject("Zresetuj swoje hasło");
		helper.setFrom("resetuj-haslo@messageboard.pl");
	}
}
