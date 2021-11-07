package me.mikolaj.messageboard.security.registration;

import me.mikolaj.messageboard.security.registration.exception.EmailNotValidateException;
import me.mikolaj.messageboard.security.registration.exception.NameNotValidateException;
import me.mikolaj.messageboard.security.registration.exception.UserAlreadyRegisteredException;
import me.mikolaj.messageboard.security.registration.exception.WeakPasswordException;
import me.mikolaj.messageboard.user.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rejestracja")
public class RegistrationController {

	private final RegistrationService registrationService;

	public RegistrationController(final RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	@GetMapping
	public String register(final Model model) {
		model.addAttribute("userDto", new UserDto());
		return "register";
	}

	@PostMapping
	public String register(@ModelAttribute final UserDto userDto, final Model model) {
		try {
			final String token = registrationService.register(userDto);
		} catch (final EmailNotValidateException exception) {
			model.addAttribute("error", "Ten email juz istnieje!");
			return "register";
		} catch (final WeakPasswordException exception) {
			model.addAttribute("error", "Podane haslo jest za słabe!");
			return "register";
		} catch (final UserAlreadyRegisteredException exception) {
			model.addAttribute("error", "Podany uzytkownik juz istnieje!");
			return "register";
		} catch (final NameNotValidateException exception) {
			model.addAttribute("error", "Wprowadź poprawne imię i nazwisko!");
			return "register";
		}
		return "redirect:/";
	}

	@GetMapping("/aktywuj")
	public String confirmRegistration(@RequestParam("token") final String token) {
		try {
			registrationService.confirmToken(token);
		} catch (final IllegalStateException exception) {
			//TODO: create error page
			return "error";
		}
		return "redirect:/";
	}
}
