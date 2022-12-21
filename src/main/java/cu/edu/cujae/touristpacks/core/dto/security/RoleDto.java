package cu.edu.cujae.touristpacks.core.dto.security;

public class RoleDto {
	private int idRole;
	private String roleName;
	private String roleDescription;

	public RoleDto() {
	}

	public RoleDto(String roleName, String roleDescription) {
		this.roleName = roleName;
		this.roleDescription = roleDescription;
	}

	public RoleDto(int idRole, String roleName, String roleDescription) {
		this(roleName, roleDescription);
		this.idRole = idRole;
	}

	public int getIdRole() {
		return this.idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return this.roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

}
