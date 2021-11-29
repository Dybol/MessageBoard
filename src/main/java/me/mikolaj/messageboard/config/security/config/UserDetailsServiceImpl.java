package me.mikolaj.messageboard.config.security.config;

import me.mikolaj.messageboard.user.User;
import me.mikolaj.messageboard.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final Optional<User> optionalUser = userRepository.findByEmail(username);
		final User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("Nie znaleziono uzytkownika"));

		return new MyUserDetails(user);
	}

}
