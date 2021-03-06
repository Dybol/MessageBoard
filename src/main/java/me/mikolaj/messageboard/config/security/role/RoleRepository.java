package me.mikolaj.messageboard.config.security.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
	public Optional<Role> findByRoleName(RoleName roleName);

}
