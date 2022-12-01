package cu.edu.cujae.touristpacks.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.RoomTypeDto;
import cu.edu.cujae.touristpacks.core.service.IRoomTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeServiceImpl implements IRoomTypeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<RoomTypeDto> getRoomTypes() throws SQLException {
        List<RoomTypeDto> list = new ArrayList<>();

        String function = "{?= call select_all_room_type()}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            CallableStatement statement = connection.prepareCall(function);
            statement.registerOutParameter(1, Types.OTHER);
            statement.execute();

            ResultSet resultSet = (ResultSet) statement.getObject(1);

            while (resultSet.next()) {
                int idRoomType = resultSet.getInt(1);
                String roomTypeName = resultSet.getString(2);

                RoomTypeDto dto = new RoomTypeDto(idRoomType, roomTypeName);
                list.add(dto);
            }
        }
        return list;
    }

    @Override
    public RoomTypeDto getRoomTypeById(int idRoomType) throws SQLException {
        RoomTypeDto roomType = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM room_type where id_room_type = ?");

            pstmt.setInt(1, idRoomType);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String roomTypeName = resultSet.getString(2);

                roomType = new RoomTypeDto(idRoomType, roomTypeName);
            }
        }

        return roomType;
    }

    @Override
    public RoomTypeDto getRoomTypeByName(String roomTypeName) throws SQLException {
        RoomTypeDto roomType = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM room_type where room_type_name = ?");

            pstmt.setString(1, roomTypeName);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int idRoomType = resultSet.getInt(1);

                roomType = new RoomTypeDto(idRoomType, roomTypeName);
            }
        }
        return roomType;
    }

    @Override
    public void createRoomType(RoomTypeDto roomType) throws SQLException {
        String function = "{call room_type_insert(?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setString(1, roomType.getRoomTypeName());
            statement.execute();
        }

    }

    @Override
    public void updateRoomType(RoomTypeDto roomType) throws SQLException {
        String function = "{call room_type_update(?,?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, roomType.getIdRoomType());
            statement.setString(2, roomType.getRoomTypeName());
            statement.execute();
        }

    }

    @Override
    public void deleteRoomType(int idRoomType) throws SQLException {
        String function = "{call room_type_delete(?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, idRoomType);
            statement.execute();
        }

    }

}
