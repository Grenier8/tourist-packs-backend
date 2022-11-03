package cu.edu.cujae.touristpacks.core.service;

import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.RoomTypeDto;

public interface IRoomTypeService {
    List<RoomTypeDto> getRoomTypes();

    RoomTypeDto getRoomTypeById(int roomTypeId);

    RoomTypeDto getRoomTypeByName(String roomTypeName);

    void createRoomType(RoomTypeDto roomType);

    void updateRoomType(RoomTypeDto roomType);

    void deleteRoomType(int idRoomType);
}
