package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.RoomPlanSeasonHotelContractDto;

public interface IRoomPlanSeasonHotelContractService {
    List<RoomPlanSeasonHotelContractDto> getRoomPlanSeasonHotelContracts() throws SQLException;

    RoomPlanSeasonHotelContractDto getRoomPlanSeasonHotelContractById(int roomPlanSeasonHotelContractId)
            throws SQLException;

    void createRoomPlanSeasonHotelContract(RoomPlanSeasonHotelContractDto roomPlanSeasonHotelContract)
            throws SQLException;

    void updateRoomPlanSeasonHotelContract(RoomPlanSeasonHotelContractDto roomPlanSeasonHotelContract)
            throws SQLException;

    void deleteRoomPlanSeasonHotelContract(int idRoomPlanSeasonHotelContract) throws SQLException;
}
