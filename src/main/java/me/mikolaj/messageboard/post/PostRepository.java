package me.mikolaj.messageboard.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

	public List<Post> getAllByCategory(String category);

	@Query("SELECT p FROM Post p ORDER BY p.addedDate DESC")
	public List<Post> findAllOrderByAddedDateDsc();

	@Query("SELECT p FROM Post p WHERE p.category=?1 ORDER BY p.addedDate DESC")
	public List<Post> findAllByCategoryOrdered(String category);

	@Query(value = "SELECT * FROM Post WHERE user_id=?1 ORDER BY added_date DESC", nativeQuery = true)
	public List<Post> findAllByUserId(Long id);

}
