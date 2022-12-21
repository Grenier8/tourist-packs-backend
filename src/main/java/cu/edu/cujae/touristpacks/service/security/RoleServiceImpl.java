package cu.edu.cujae.touristpacks.service.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cu.edu.cujae.touristpacks.core.dto.security.RoleDto;
import cu.edu.cujae.touristpacks.core.service.security.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public RoleDto getRoleById(int idRole) throws SQLException {
		RoleDto role = null;

		try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
			PreparedStatement pstmt = connection.prepareStatement(
					"SELECT * FROM role WHERE id_role = ?");

			pstmt.setInt(1, idRole);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String roleName = rs.getString(2);
				String roleDescription = rs.getString(3);

				role = new RoleDto(idRole, roleName, roleDescription);
			}

		}
		return role;
	}

	@Override
	public RoleDto getRoleByName(String roleName) throws SQLException {
		RoleDto role = null;

		try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
			PreparedStatement pstmt = connection.prepareStatement(
					"SELECT * FROM role where role_name = ?");

			pstmt.setString(1, roleName);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int idRole = resultSet.getInt(1);
				String roleDescription = resultSet.getString(3);

				role = new RoleDto(idRole, roleName, roleDescription);
			}
		}

		return role;
	}

	@Override
	public List<RoleDto> getRoles() throws SQLException {
		List<RoleDto> list = new ArrayList<>();

		try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
			PreparedStatement pstmt = connection.prepareStatement(
					"SELECT * FROM role");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int idRole = rs.getInt(1);
				String roleName = rs.getString(2);
				String roleDescription = rs.getString(3);

				list.add(new RoleDto(idRole, roleName, roleDescription));
			}

		}
		return list;
	}

	@Override
	public void createRole(RoleDto role) throws SQLException {
		String function = "INSERT INTO role (role_name, role_description) VALUES (?,?)";

		try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
			PreparedStatement statement = connection.prepareStatement(function);

			statement.setString(1, role.getRoleName());
			statement.setString(2, role.getRoleDescription());
			statement.executeUpdate();
		}

	}

	@Override
	public void updateRole(RoleDto role) throws SQLException {
		String function = "UPDATE role SET role_name = ?, role_description = ? WHERE id_role = ?; ";

		try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
			PreparedStatement statement = connection.prepareStatement(function);

			statement.setString(1, role.getRoleName());
			statement.setString(2, role.getRoleDescription());
			statement.setInt(3, role.getIdRole());
			statement.executeUpdate();
		}
	}

	@Override
	public void deleteRole(int idRole) throws SQLException {
		String function = "DELETE FROM role WHERE id_role = ?; ";

		try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
			PreparedStatement statement = connection.prepareStatement(function);
			statement.setInt(1, idRole);
			statement.executeUpdate();
		}

	}

}
