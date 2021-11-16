package me.mikolaj.messageboard.security;

import me.mikolaj.messageboard.security.registration.exception.WeakPasswordException;
import me.mikolaj.messageboard.security.registration.token.ConfirmationToken;
import me.mikolaj.messageboard.security.registration.token.ConfirmationTokenService;
import me.mikolaj.messageboard.user.exception.UserNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/resetuj-haslo")
public class ForgotPasswordController {

	private final ConfirmationTokenService confirmationTokenService;
	private final ForgotPasswordService passwordService;

	public ForgotPasswordController(final ConfirmationTokenService confirmationTokenService, final ForgotPasswordService passwordService) {
		this.confirmationTokenService = confirmationTokenService;
		this.passwordService = passwordService;
	}

	@GetMapping
	public String getForm(@RequestParam(required = false) final String token, final Model model) {
		if (token == null)
			return "forgotPassword";
		else {
			final ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(() ->
					new IllegalStateException("Nie znaleziono tokenu!"));

			model.addAttribute("token", confirmationToken.getToken());
			return "changePassword";
		}
	}

	@PostMapping
	public String resetPassword(@RequestParam final String email, final Model model) {
		try {
			passwordService.sendPasswordResetEmail(email);
			model.addAttribute("info", "Wysłaliśmy na twojego maila link do zresetowania hasła");
		} catch (final UserNotFoundException exception) {
			model.addAttribute("info", "Nie znaleziono uzytkownika o podanym mailu!");
		}
		return "forgotPassword";
	}

	@PostMapping("/zmien")
	public String changePassword(@RequestParam final String password, @RequestParam final String token,
								 final Model model, final RedirectAttributes attributes) {
		try {
			passwordService.changePassword(password, token);
			model.addAttribute("message", "Zmiana hasła powiodła się!");
		} catch (final WeakPasswordException exception) {
			attributes.addFlashAttribute("error", "Podane haslo jest za slabe!");
			return "redirect:/resetuj-haslo?token=" + token;
		} catch (final IllegalStateException exception) {
			attributes.addFlashAttribute("error", exception.getMessage());
			return "redirect:/resetuj-haslo?token=" + token;
		}
		return "login";
	}
}
