package me.mikolaj.messageboard.config.security.config;

import me.mikolaj.messageboard.config.security.role.Role;
import me.mikolaj.messageboard.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class MyUserDetails implements UserDetails {

	private final User user;

	public MyUserDetails(final User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		final Set<Role> roles = user.getRoles();
		final List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (final Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName().name()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}
}
