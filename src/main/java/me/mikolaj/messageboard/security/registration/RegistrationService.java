package me.mikolaj.messageboard.security.registration;

import me.mikolaj.messageboard.email.EmailProducer;
import me.mikolaj.messageboard.email.EmailSender;
import me.mikolaj.messageboard.email.EmailValidator;
import me.mikolaj.messageboard.newsletter.NewsletterService;
import me.mikolaj.messageboard.security.registration.exception.EmailNotValidateException;
import me.mikolaj.messageboard.security.registration.exception.NameNotValidateException;
import me.mikolaj.messageboard.security.registration.exception.WeakPasswordException;
import me.mikolaj.messageboard.security.registration.token.ConfirmationToken;
import me.mikolaj.messageboard.security.registration.token.ConfirmationTokenService;
import me.mikolaj.messageboard.security.registration.validation.NameValidator;
import me.mikolaj.messageboard.security.registration.validation.PasswordValidator;
import me.mikolaj.messageboard.security.role.RoleName;
import me.mikolaj.messageboard.security.role.RoleService;
import me.mikolaj.messageboard.user.User;
import me.mikolaj.messageboard.user.UserDto;
import me.mikolaj.messageboard.user.UserMapper;
import me.mikolaj.messageboard.user.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class RegistrationService {

	private final EmailValidator emailValidator;
	private final PasswordValidator passwordValidator;
	private final NameValidator nameValidator;
	private final UserService userService;
	private final UserMapper userMapper;
	private final RoleService roleService;
	private final ConfirmationTokenService confirmationTokenService;
	private final EmailSender emailSender;
	private final NewsletterService newsletterService;

	public RegistrationService(final EmailValidator emailValidator,
							   final PasswordValidator passwordValidator, final NameValidator nameValidator, final UserService userService,
							   final UserMapper userMapper,
							   final RoleService roleService,
							   final ConfirmationTokenService confirmationTokenService,
							   @EmailProducer(type = EmailProducer.ProducerType.REGISTRATION) final EmailSender emailSender,
							   final NewsletterService newsletterService) {
		this.emailValidator = emailValidator;
		this.passwordValidator = passwordValidator;
		this.nameValidator = nameValidator;
		this.userService = userService;
		this.userMapper = userMapper;
		this.roleService = roleService;
		this.confirmationTokenService = confirmationTokenService;
		this.emailSender = emailSender;
		this.newsletterService = newsletterService;
	}

	public String register(final UserDto userDto, final String checked) {
		final boolean isValidEmail = emailValidator.test(userDto.getEmail());
		final boolean isValidPassword = passwordValidator.test(userDto.getPassword());
		final boolean isValidFirstName = nameValidator.test(userDto.getFirstName());
		final boolean isValidLastName = nameValidator.test(userDto.getLastName());

		if (!isValidEmail) {
			throw new EmailNotValidateException();
		}

		if (!isValidPassword) {
			throw new WeakPasswordException();
		}

		if (!isValidLastName || !isValidFirstName) {
			throw new NameNotValidateException();
		}

		final User user = userMapper.toEntity(userDto);
		roleService.assignRoleToUser(user, RoleName.USER);

		user.setEnabled(false);

		if (checked != null)
			newsletterService.addEmailToNewsletter(userDto.getEmail());

		final String token = userService.signUpUser(user);
		final String link = "http://localhost:8080/rejestracja/aktywuj?token=" + token;
		emailSender.send(user.getEmail(), buildEmail(user.getFirstName(), link));
		return token;
	}

	@Transactional
	public String confirmToken(final String token) {
		final ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(() ->
				new IllegalStateException("Nie znaleziono tokenu!"));

		if (confirmationToken.getConfirmedAt() != null) {
			throw new IllegalStateException("Ten email zostal juz potwierdzony!");
		}

		if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
			throw new IllegalStateException("Token wygasł");
		}
		confirmationTokenService.setConfirmedAt(token);
		userService.enableUser(confirmationToken.getUser().getEmail());
		return "confirmed";
	}

	private String buildEmail(final String name, final String link) {
		return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
				"\n" +
				"<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
				"\n" +
				"  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
				"    <tbody><tr>\n" +
				"      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
				"        \n" +
				"        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
				"          <tbody><tr>\n" +
				"            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
				"                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
				"                  <tbody><tr>\n" +
				"                    <td style=\"padding-left:10px\">\n" +
				"                  \n" +
				"                    </td>\n" +
				"                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
				"                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
				"                    </td>\n" +
				"                  </tr>\n" +
				"                </tbody></table>\n" +
				"              </a>\n" +
				"            </td>\n" +
				"          </tr>\n" +
				"        </tbody></table>\n" +
				"        \n" +
				"      </td>\n" +
				"    </tr>\n" +
				"  </tbody></table>\n" +
				"  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
				"    <tbody><tr>\n" +
				"      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
				"      <td>\n" +
				"        \n" +
				"                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
				"                  <tbody><tr>\n" +
				"                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
				"                  </tr>\n" +
				"                </tbody></table>\n" +
				"        \n" +
				"      </td>\n" +
				"      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
				"    </tr>\n" +
				"  </tbody></table>\n" +
				"\n" +
				"\n" +
				"\n" +
				"  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
				"    <tbody><tr>\n" +
				"      <td height=\"30\"><br></td>\n" +
				"    </tr>\n" +
				"    <tr>\n" +
				"      <td width=\"10\" valign=\"middle\"><br></td>\n" +
				"      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
				"        \n" +
				"            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
				"        \n" +
				"      </td>\n" +
				"      <td width=\"10\" valign=\"middle\"><br></td>\n" +
				"    </tr>\n" +
				"    <tr>\n" +
				"      <td height=\"30\"><br></td>\n" +
				"    </tr>\n" +
				"  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
				"\n" +
				"</div></div>";
	}
}
