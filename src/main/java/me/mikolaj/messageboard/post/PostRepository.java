package me.mikolaj.messageboard.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

	public List<Post> getAllByCategory(String category);

	@Query("SELECT p FROM Post p ORDER BY p.addedDate DESC")
	public List<Post> findAllOrderByAddedDateDsc();
}
