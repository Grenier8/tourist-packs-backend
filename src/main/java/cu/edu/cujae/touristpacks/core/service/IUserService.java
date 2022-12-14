package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.UserDto;

public interface IUserService {

	void createUser(UserDto user) throws SQLException;

	void updateUser(UserDto user) throws SQLException;

	List<UserDto> getUsers() throws SQLException;

	UserDto getUserById(int idUser) throws SQLException;

	void deleteUser(int idUser) throws SQLException;
}
