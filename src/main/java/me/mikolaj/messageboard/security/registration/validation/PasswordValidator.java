package me.mikolaj.messageboard.security.registration.validation;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class PasswordValidator implements Predicate<String> {

	@Override
	public boolean test(final String s) {
		if (!s.matches(".*\\d.*"))
			return false;
		if (s.chars().noneMatch(Character::isUpperCase))
			return false;
		if (s.chars().noneMatch(Character::isLowerCase))
			return false;
		if (s.length() < 8)
			return false;

		return true;
	}
}
