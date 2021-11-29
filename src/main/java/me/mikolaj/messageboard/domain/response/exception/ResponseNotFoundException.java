package me.mikolaj.messageboard.domain.response.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Nie znaleziono odpowiedzi!")
public class ResponseNotFoundException extends RuntimeException {
}
