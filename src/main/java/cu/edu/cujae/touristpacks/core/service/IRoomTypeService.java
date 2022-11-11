package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.RoomTypeDto;

public interface IRoomTypeService {
    List<RoomTypeDto> getRoomTypes() throws SQLException;

    RoomTypeDto getRoomTypeById(int roomTypeId) throws SQLException;

    RoomTypeDto getRoomTypeByName(String roomTypeName) throws SQLException;

    void createRoomType(RoomTypeDto roomType) throws SQLException;

    void updateRoomType(RoomTypeDto roomType) throws SQLException;

    void deleteRoomType(int idRoomType) throws SQLException;
}
