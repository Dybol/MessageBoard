package me.mikolaj.messageboard.security.registration.token;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenService {

	private final ConfirmationTokenRepository confirmationTokenRepository;

	public ConfirmationTokenService(final ConfirmationTokenRepository confirmationTokenRepository) {
		this.confirmationTokenRepository = confirmationTokenRepository;
	}

	public void saveConfirmationToken(final ConfirmationToken token) {
		confirmationTokenRepository.save(token);
	}

	public Optional<ConfirmationToken> getToken(final String token) {
		return confirmationTokenRepository.findByToken(token);
	}

	public void setConfirmedAt(final String token) {
		final Optional<ConfirmationToken> confirmationTokenOptional = confirmationTokenRepository.findByToken(token);
		final ConfirmationToken confirmationToken = confirmationTokenOptional.orElseThrow(() -> new IllegalStateException("Nie znaleziono tokenu!"));
		confirmationToken.setConfirmedAt(LocalDateTime.now());
		confirmationTokenRepository.save(confirmationToken);
	}
}
