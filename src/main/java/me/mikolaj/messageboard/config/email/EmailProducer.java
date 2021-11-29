package me.mikolaj.messageboard.config.email;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,
		ElementType.METHOD,
		ElementType.TYPE,
		ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface EmailProducer {
	ProducerType type();

	public enum ProducerType {
		REGISTRATION, NEWSLETTER, PASSWORD;
	}
}
