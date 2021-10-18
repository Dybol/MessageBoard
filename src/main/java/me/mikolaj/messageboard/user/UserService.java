package me.mikolaj.messageboard.user;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public UserService(final UserRepository userRepository, final UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	public Optional<UserDto> findByEmail(final String email) {
		return userRepository.findByEmail(email).map(userMapper::toDto);
	}
}
