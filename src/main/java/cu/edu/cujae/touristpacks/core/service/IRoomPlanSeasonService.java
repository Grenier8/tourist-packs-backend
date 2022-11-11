package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.RoomPlanSeasonDto;

public interface IRoomPlanSeasonService {
    List<RoomPlanSeasonDto> getRoomPlanSeasons() throws SQLException;

    RoomPlanSeasonDto getRoomPlanSeasonById(int roomPlanSeasonId) throws SQLException;

    RoomPlanSeasonDto getRoomPlanSeasonByName(String roomPlanSeasonName) throws SQLException;

    void createRoomPlanSeason(RoomPlanSeasonDto roomPlanSeason) throws SQLException;

    void updateRoomPlanSeason(RoomPlanSeasonDto roomPlanSeason) throws SQLException;

    void deleteRoomPlanSeason(int idRoomPlanSeason) throws SQLException;
}
