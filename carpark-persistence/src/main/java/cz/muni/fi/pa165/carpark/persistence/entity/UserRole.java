package cz.muni.fi.pa165.carpark.persistence.entity;

/**
 *
 * @author Jakub Kriz
 */
public enum UserRole {
    ROLE_EMPLOYEE("ROLE_EMPLOYEE"), ROLE_ADMIN("ROLE_ADMIN");

	private String role;

	UserRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return this.role;
	}

	@Override
	public String toString() {
		return getRole();
	}
}
