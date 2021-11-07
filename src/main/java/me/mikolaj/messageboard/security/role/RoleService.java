package me.mikolaj.messageboard.security.role;

import me.mikolaj.messageboard.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

	private final RoleRepository roleRepository;

	public RoleService(final RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public void assignRoleToUser(final User user, final RoleName roleName) {
		final Optional<Role> optionalRole = roleRepository.findByRoleName(roleName);
		optionalRole.ifPresent(user::addRole);
	}
}
