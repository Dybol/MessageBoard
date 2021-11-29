package me.mikolaj.messageboard.config.email;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {
	@Override
	public boolean test(final String email) {
		if (!email.contains("@") || !email.contains(".") || email.split("@")[0].length() <= 0)
			return false;

		return true;
	}
}
