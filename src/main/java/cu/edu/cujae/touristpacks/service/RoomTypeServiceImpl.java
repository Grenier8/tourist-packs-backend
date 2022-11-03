package cu.edu.cujae.touristpacks.service;

import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.RoomTypeDto;
import cu.edu.cujae.touristpacks.core.service.IRoomTypeService;

import org.springframework.stereotype.Service;

@Service
public class RoomTypeServiceImpl implements IRoomTypeService {

    @Override
    public List<RoomTypeDto> getRoomTypes() {
        List<RoomTypeDto> list = new ArrayList<>();
        list.add(new RoomTypeDto(1, "Doble"));
        list.add(new RoomTypeDto(2, "Sencilla"));

        return list;
    }

    @Override
    public RoomTypeDto getRoomTypeById(int roomTypeId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RoomTypeDto getRoomTypeByName(String roomTypeName) {
        return getRoomTypes().stream().filter(r -> r.getRoomTypeName().equals(roomTypeName)).findFirst().get();
    }

    @Override
    public void createRoomType(RoomTypeDto roomType) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateRoomType(RoomTypeDto roomType) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRoomType(int idRoomType) {
        // TODO Auto-generated method stub

    }

}
