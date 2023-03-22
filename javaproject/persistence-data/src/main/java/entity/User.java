package entity;

import java.time.LocalDate;

public class User extends Entity {
	public static final int SUPER_ADMIN_ROLe = 1;
	public static final int ADMIN_ROLE = 2;
	public static final int USER_ROLE = 3;
	// Account information
	private String email;
	private String password;
	private Integer roleId;

	private LocalDate createdAt;
	private LocalDate updatedAt;

	public User() {
		super();
	}
	
	public static int getUserRole() {
		return USER_ROLE;
	}

	public User(String email, String password, Integer roleId) {
		super();
		this.email = email;
		this.password = password;
		this.roleId = roleId;
	}

	public User(Integer id, String email, String password, Integer roleId) {
		super(id);
		this.email = email;
		this.password = password;
		this.roleId = roleId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", RoleId=" + roleId + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
