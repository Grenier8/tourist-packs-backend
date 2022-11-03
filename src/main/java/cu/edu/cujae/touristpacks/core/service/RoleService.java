package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.RoleDto;

public interface RoleService {

	List<RoleDto> getRolesByUserId(String userId) throws SQLException;

}
