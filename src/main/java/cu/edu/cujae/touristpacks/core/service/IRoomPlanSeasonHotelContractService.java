package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.RoomPlanSeasonDto;
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

        List<RoomPlanSeasonDto> getRoomPlanSeasonsByIdHotelContract(int idHotelContract) throws SQLException;

        void deleteRoomPlanSeasonHotelContractByIds(int idHotelContract, int idRoomPlanSeason) throws SQLException;

        void deleteRoomPlanSeasonHotelContractByHotelContract(int idHotelContract) throws SQLException;
}
