package me.mikolaj.messageboard.security.role;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}
