package cu.edu.cujae.touristpacks.core.service.security;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.security.UserDto;

public interface IUserService {

	void createUser(UserDto user) throws SQLException;

	void updateUser(UserDto user) throws SQLException;

	List<UserDto> getUsers() throws SQLException;

	UserDto getUserById(int idUser) throws SQLException;

	UserDto getUserByUsername(String username) throws SQLException;

	void deleteUser(int idUser) throws SQLException;
}
