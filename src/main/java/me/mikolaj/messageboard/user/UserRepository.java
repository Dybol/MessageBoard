package me.mikolaj.messageboard.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByEmail(String email);

	public Optional<User> findByUsername(String authorUsername);

	@Query("SELECT u FROM User u ORDER BY u.joinedAt DESC")
	public List<User> findAllOrderByJoinedAtDesc();
}
