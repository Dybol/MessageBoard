package me.mikolaj.messageboard.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Nie znaleziono użytkownika!")
public class UserNotFoundException extends RuntimeException {
}
