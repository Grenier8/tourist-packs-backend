package cu.edu.cujae.touristpacks.core.service;

import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.RoomPlanSeasonDto;

public interface IRoomPlanSeasonService {
    List<RoomPlanSeasonDto> getRoomPlanSeasons();

    RoomPlanSeasonDto getRoomPlanSeasonById(int roomPlanSeasonId);

    RoomPlanSeasonDto getRoomPlanSeasonByName(String roomPlanSeasonName);

    void createRoomPlanSeason(RoomPlanSeasonDto roomPlanSeason);

    void updateRoomPlanSeason(RoomPlanSeasonDto roomPlanSeason);

    void deleteRoomPlanSeason(int idRoomPlanSeason);
}
