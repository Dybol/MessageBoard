package me.mikolaj.messageboard.response;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, Long> {

	public List<Response> findAllByPostId(Long id);
}
