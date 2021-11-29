package me.mikolaj.messageboard.config.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		authenticationProvider.setUserDetailsService(userDetailsService());

		return authenticationProvider;
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/admin").hasAnyAuthority("ADMIN")
				.antMatchers("/admin/**").hasAnyAuthority("ADMIN")
				.antMatchers("/moje-posty").hasAnyAuthority("ADMIN", "USER")
				.anyRequest().permitAll()
				.and().formLogin()
				.loginPage("/login").failureUrl("/login-error").permitAll()
				.and().logout().logoutSuccessUrl("/").permitAll();
	}

	@Override
	public void configure(final WebSecurity web) {
		web.ignoring().antMatchers("/css/**");
	}
}
