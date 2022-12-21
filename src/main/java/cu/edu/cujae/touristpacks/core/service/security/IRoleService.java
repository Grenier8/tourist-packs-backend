package cu.edu.cujae.touristpacks.core.service.security;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.security.RoleDto;

public interface IRoleService {

	List<RoleDto> getRoles() throws SQLException;

	void createRole(RoleDto role) throws SQLException;

	void updateRole(RoleDto role) throws SQLException;

	void deleteRole(int idRole) throws SQLException;

	RoleDto getRoleById(int roleId) throws SQLException;

	RoleDto getRoleByName(String roleName) throws SQLException;

}
