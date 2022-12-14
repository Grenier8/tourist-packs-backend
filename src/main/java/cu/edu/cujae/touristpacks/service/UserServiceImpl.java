package cu.edu.cujae.touristpacks.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cu.edu.cujae.touristpacks.core.dto.RoleDto;
import cu.edu.cujae.touristpacks.core.dto.UserDto;
import cu.edu.cujae.touristpacks.core.service.IRoleService;
import cu.edu.cujae.touristpacks.core.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private IRoleService roleService;

	@Override
	public void createUser(UserDto user) throws SQLException {
		String function = "INSERT INTO user_table(username, password, name, id_role, email) VALUES(?,?,?,?,?);";

		try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
			PreparedStatement statement = connection.prepareStatement(function);

			statement.setString(1, user.getUsername());
			statement.setString(2, getMd5Hash(user.getPassword()));
			statement.setString(3, user.getName());
			statement.setInt(4, user.getRole().getIdRole());
			statement.setString(5, user.getEmail());
			statement.executeUpdate();
		}
	}

	@Override
	public List<UserDto> getUsers() throws SQLException {
		List<UserDto> userList = new ArrayList<>();

		try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
			PreparedStatement pstmt = connection.prepareStatement(
					"SELECT * FROM user_table");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int idUser = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				String name = rs.getString(4);
				int idRole = rs.getInt(5);
				String email = rs.getString(6);

				RoleDto role = roleService.getRoleById(idRole);

				userList.add(new UserDto(idUser, username, password, name, role, email));
			}
		}
		return userList;
	}

	@Override
	public void updateUser(UserDto user) throws SQLException {
		String function = "UPDATE user_table SET username = ?, password = ?, name = ?, id_role = ?, email = ? WHERE id_user = ?;";

		try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
			PreparedStatement statement = connection.prepareStatement(function);

			statement.setString(1, user.getUsername());
			statement.setString(2, getMd5Hash(user.getPassword()));
			statement.setString(3, user.getName());
			statement.setInt(4, user.getRole().getIdRole());
			statement.setString(5, user.getEmail());
			statement.setInt(6, user.getIdUser());
			statement.executeUpdate();
		}

	}

	@Override
	public UserDto getUserById(int idUser) throws SQLException {
		UserDto user = null;

		try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {

			PreparedStatement pstmt = connection.prepareStatement(
					"SELECT * FROM user_table WHERE id_user = ?");

			pstmt.setInt(1, idUser);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				String username = resultSet.getString(2);
				String password = resultSet.getString(3);
				String name = resultSet.getString(4);
				int idRole = resultSet.getInt(5);
				String email = resultSet.getString(6);

				RoleDto role = roleService.getRoleById(idRole);

				user = new UserDto(idUser, username, password, name, role, email);
			}
		}
		return user;
	}

	@Override
	public void deleteUser(int userId) throws SQLException {

		try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
			PreparedStatement pstmt = connection.prepareStatement(
					"DELETE FROM user_table WHERE id_user = ?");

			pstmt.setInt(1, userId);
			pstmt.executeUpdate();
		}
	}

	private String getMd5Hash(String password) {
		MessageDigest md;
		String md5Hash = "";
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			md5Hash = DatatypeConverter
					.printHexBinary(digest).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5Hash;
	}

}
