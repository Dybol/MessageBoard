package me.mikolaj.messageboard.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatMessage, Long> {

	@Query(value = "SELECT * FROM chat_message ORDER BY sent_at DESC LIMIT 20", nativeQuery = true)
	public List<ChatMessage> find20LastMessages();
}
