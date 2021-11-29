package me.mikolaj.messageboard.config.security.role;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private RoleName roleName;

	public Role() {
	}

	public Role(final RoleName roleName) {
		this.roleName = roleName;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public RoleName getRoleName() {
		return roleName;
	}

	public void setRoleName(final RoleName roleName) {
		this.roleName = roleName;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final Role role = (Role) o;
		return Objects.equals(id, role.id) && roleName == role.roleName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, roleName);
	}
}
