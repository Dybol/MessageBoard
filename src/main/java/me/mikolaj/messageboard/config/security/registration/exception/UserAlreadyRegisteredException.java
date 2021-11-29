package me.mikolaj.messageboard.config.security.registration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Podany użytkownik już istnieje!")
public class UserAlreadyRegisteredException extends RuntimeException {
}
