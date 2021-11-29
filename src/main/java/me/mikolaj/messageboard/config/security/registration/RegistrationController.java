package me.mikolaj.messageboard.config.security.registration;

import me.mikolaj.messageboard.config.security.registration.exception.EmailNotValidateException;
import me.mikolaj.messageboard.config.security.registration.exception.NameNotValidateException;
import me.mikolaj.messageboard.config.security.registration.exception.UserAlreadyRegisteredException;
import me.mikolaj.messageboard.config.security.registration.exception.WeakPasswordException;
import me.mikolaj.messageboard.user.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String register(@ModelAttribute final UserDto userDto, final Model model, @RequestParam(required = false) final String checked,
						   final RedirectAttributes attributes) {
		try {
			final String token = registrationService.register(userDto, checked);
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
		attributes.addFlashAttribute("message", "Potwierdź swoją rejestrację poprzez kliknięcie w link wysłany w mailu!");
		return "redirect:/rejestracja";
	}

	@GetMapping("/aktywuj")
	public String confirmRegistration(@RequestParam("token") final String token, final RedirectAttributes attributes) {
		try {
			registrationService.confirmToken(token);
		} catch (final IllegalStateException exception) {
			//TODO: create error page
			return "error";
		}
		attributes.addFlashAttribute("message", "Twój email został potwierdzony. Możesz się zalogować");
		return "redirect:/login";
	}
}
