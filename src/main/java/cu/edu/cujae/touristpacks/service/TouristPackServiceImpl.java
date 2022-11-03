package cu.edu.cujae.touristpacks.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.HotelDto;
import cu.edu.cujae.touristpacks.core.dto.RoomPlanSeasonDto;
import cu.edu.cujae.touristpacks.core.dto.TouristPackDto;
import cu.edu.cujae.touristpacks.core.service.IHotelService;
import cu.edu.cujae.touristpacks.core.service.IRoomPlanSeasonService;
import cu.edu.cujae.touristpacks.core.service.ITouristPackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TouristPackServiceImpl implements ITouristPackService {

    @Autowired
    IHotelService hotelService;

    @Autowired
    IRoomPlanSeasonService roomPlanSeasonService;

    @Override
    public List<TouristPackDto> getTouristPacks() throws SQLException {
        List<TouristPackDto> list = new ArrayList<>();

        HotelDto hotel1 = hotelService.getHotels().get(0);
        HotelDto hotel2 = hotelService.getHotels().get(1);

        RoomPlanSeasonDto roomPlanSeason1 = roomPlanSeasonService.getRoomPlanSeasons().get(0);
        RoomPlanSeasonDto roomPlanSeason2 = roomPlanSeasonService.getRoomPlanSeasons().get(1);

        list.add(new TouristPackDto(1, "Pak1", 2, 1, 3, 12, hotel1, roomPlanSeason1));
        list.add(new TouristPackDto(1, "Pak2", 2, 3, 3, 11, hotel2, roomPlanSeason2));

        return list;
    }

    @Override
    public TouristPackDto getTouristPackById(int touristPackId) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TouristPackDto getTouristPackByName(String touristPackName) throws SQLException {
        return getTouristPacks().stream().filter(r -> r.getPromotionalName().equals(touristPackName)).findFirst().get();
    }

    @Override
    public void createTouristPack(TouristPackDto touristPack) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateTouristPack(TouristPackDto touristPack) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteTouristPack(int idTouristPack) throws SQLException {
        // TODO Auto-generated method stub

    }

}
