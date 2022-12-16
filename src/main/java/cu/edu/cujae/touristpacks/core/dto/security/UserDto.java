package cu.edu.cujae.touristpacks.core.dto.security;

public class UserDto {
	private int idUser;
	private String username;
	private String password;
	private String name;
	private RoleDto role;
	private String email;

	public UserDto() {

	}

	public UserDto(String username, String password, String name, RoleDto role, String email) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.role = role;
		this.email = email;
	}

	public UserDto(int idUser, String username, String password, String name, RoleDto role, String email) {
		this(username, password, name, role, email);
		this.idUser = idUser;
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RoleDto getRole() {
		return this.role;
	}

	public void setRole(RoleDto role) {
		this.role = role;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
