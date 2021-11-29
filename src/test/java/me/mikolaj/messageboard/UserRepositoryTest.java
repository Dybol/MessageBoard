package me.mikolaj.messageboard;

import me.mikolaj.messageboard.user.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class UserRepositoryTest {

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testFindAll() {
		Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(
				new User("Mikolaj", "Nowak", "mikolaj@gmail.com", "pass123", "miki123", LocalDateTime.now()),
				new User("Adam", "Kowalski", "adam@gmail.com", "password", "kowalski", LocalDateTime.now()))
		);

		final List<UserDto> users = userService.findAll();
		Assertions.assertNotNull(users);
		Assertions.assertEquals(2, users.size());
	}

	@Test
	public void testFindByEmail() {
		final User user = new User("Mikolaj", "Nowak", "mikolaj@gmail.com", "pass123", "miki123", LocalDateTime.now());
		doReturn(Optional.of(user)).when(userRepository).findByEmail("mikolaj@gmail.com");

		final Optional<UserDto> returnedUser = userService.findByEmail("mikolaj@gmail.com");

		Assertions.assertTrue(returnedUser.isPresent());
		Assertions.assertEquals(returnedUser.get(), userMapper.toDto(user));

	}
}
