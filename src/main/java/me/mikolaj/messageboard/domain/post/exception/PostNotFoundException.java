package me.mikolaj.messageboard.domain.post.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Nie znaleziono posta!")
public class PostNotFoundException extends RuntimeException {
}
