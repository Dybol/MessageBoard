package me.mikolaj.messageboard.config.security;

import me.mikolaj.messageboard.config.email.EmailProducer;
import me.mikolaj.messageboard.config.email.EmailSender;
import me.mikolaj.messageboard.config.security.registration.exception.WeakPasswordException;
import me.mikolaj.messageboard.config.security.registration.token.ConfirmationToken;
import me.mikolaj.messageboard.config.security.registration.token.ConfirmationTokenService;
import me.mikolaj.messageboard.config.security.registration.validation.PasswordValidator;
import me.mikolaj.messageboard.user.User;
import me.mikolaj.messageboard.user.UserDto;
import me.mikolaj.messageboard.user.UserMapper;
import me.mikolaj.messageboard.user.UserService;
import me.mikolaj.messageboard.user.exception.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ForgotPasswordService {

	private final ConfirmationTokenService confirmationTokenService;
	private final PasswordValidator passwordValidator;
	private final PasswordEncoder passwordEncoder;
	private final UserService userService;
	private final UserMapper userMapper;
	private final EmailSender emailSender;

	public ForgotPasswordService(final ConfirmationTokenService confirmationTokenService, final PasswordValidator passwordValidator,
								 final PasswordEncoder passwordEncoder, final UserService userService, final UserMapper userMapper,
								 @EmailProducer(type = EmailProducer.ProducerType.PASSWORD) final EmailSender emailSender) {
		this.confirmationTokenService = confirmationTokenService;
		this.passwordValidator = passwordValidator;
		this.passwordEncoder = passwordEncoder;
		this.userService = userService;
		this.userMapper = userMapper;
		this.emailSender = emailSender;
	}

	@Transactional
	public void changePassword(final String password, final String token) {
		final ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(() ->
				new IllegalStateException("Nie znaleziono tokenu!"));

		if (confirmationToken.getConfirmedAt() != null) {
			throw new IllegalStateException("Hasło zostało już zmienione!");
		}

		final LocalDateTime expiredAt = confirmationToken.getExpiresAt();
		if (expiredAt.isBefore(LocalDateTime.now())) {
			throw new IllegalStateException("Token wygasł");
		}

		if (!passwordValidator.test(password))
			throw new WeakPasswordException();

		confirmationToken.setConfirmedAt(LocalDateTime.now());

		final User user = confirmationToken.getUser();
		user.setPassword(passwordEncoder.encode(password));
	}

	public void sendPasswordResetEmail(final String to) {
		final Optional<UserDto> userDtoOptional = userService.findByEmail(to);
		final UserDto userDto = userDtoOptional.orElseThrow(UserNotFoundException::new);

		final User user = userMapper.toEntity(userDto);

		final ConfirmationToken confirmationToken = new ConfirmationToken(
				UUID.randomUUID().toString(),
				LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15L),
				user
		);

		confirmationTokenService.saveConfirmationToken(confirmationToken);

		final String link = "http://localhost:8080/resetuj-haslo?token=" + confirmationToken.getToken();

		emailSender.send(to, "Kliknij w ponizszy link, aby zresetować hasło: " + link);
	}
}
