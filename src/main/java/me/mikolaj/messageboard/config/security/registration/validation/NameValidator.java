package me.mikolaj.messageboard.config.security.registration.validation;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class NameValidator implements Predicate<String> {
	@Override
	public boolean test(final String s) {
		return !s.matches(".*\\d.*") && s.length() > 1;
	}
}
