package me.mikolaj.messageboard.user;

import me.mikolaj.messageboard.security.registration.exception.UserAlreadyRegisteredException;
import me.mikolaj.messageboard.security.registration.token.ConfirmationToken;
import me.mikolaj.messageboard.security.registration.token.ConfirmationTokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final BCryptPasswordEncoder passwordEncoder;
	private final ConfirmationTokenService confirmationTokenService;

	public UserService(final UserRepository userRepository, final UserMapper userMapper, final BCryptPasswordEncoder passwordEncoder, final ConfirmationTokenService confirmationTokenService) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.passwordEncoder = passwordEncoder;
		this.confirmationTokenService = confirmationTokenService;
	}

	public Optional<UserDto> findByEmail(final String email) {
		return userRepository.findByEmail(email).map(userMapper::toDto);
	}

	public String signUpUser(final User user) {
		final boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

		if (userExists) {
			throw new UserAlreadyRegisteredException();
		}

		final String encodedPassword = passwordEncoder.encode(user.getPassword());

		user.setPassword(encodedPassword);
		userRepository.save(user);

		final String token = UUID.randomUUID().toString();

		final ConfirmationToken confirmationToken = new ConfirmationToken(
				token,
				LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15),
				user
		);

		confirmationTokenService.saveConfirmationToken(confirmationToken);

		return token;
	}

	public void enableUser(final String email) {
		final Optional<User> optionalUser = userRepository.findByEmail(email);
		final User user = optionalUser.orElseThrow(() -> new IllegalStateException("Nie znaleziono uzytkownika!"));
		user.setEnabled(true);
		userRepository.save(user);
	}
}
