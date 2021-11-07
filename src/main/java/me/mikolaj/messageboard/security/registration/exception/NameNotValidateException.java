package me.mikolaj.messageboard.security.registration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Wprowadź poprawne imię i nazwisko!")
public class NameNotValidateException extends RuntimeException {
}
