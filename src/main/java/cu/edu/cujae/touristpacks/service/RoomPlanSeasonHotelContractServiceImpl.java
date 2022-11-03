package cu.edu.cujae.touristpacks.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.HotelContractDto;
import cu.edu.cujae.touristpacks.core.dto.RoomPlanSeasonDto;
import cu.edu.cujae.touristpacks.core.dto.RoomPlanSeasonHotelContractDto;
import cu.edu.cujae.touristpacks.core.service.IHotelContractService;
import cu.edu.cujae.touristpacks.core.service.IRoomPlanSeasonHotelContractService;
import cu.edu.cujae.touristpacks.core.service.IRoomPlanSeasonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomPlanSeasonHotelContractServiceImpl implements IRoomPlanSeasonHotelContractService {

    @Autowired
    IRoomPlanSeasonService roomPlanSeasonService;

    @Autowired
    IHotelContractService hotelContractService;

    @Override
    public List<RoomPlanSeasonHotelContractDto> getRoomPlanSeasonHotelContracts() throws SQLException {
        List<RoomPlanSeasonHotelContractDto> list = new ArrayList<>();

        RoomPlanSeasonDto roomPlanSeason1 = roomPlanSeasonService.getRoomPlanSeasons().get(0);
        RoomPlanSeasonDto roomPlanSeason2 = roomPlanSeasonService.getRoomPlanSeasons().get(1);

        HotelContractDto hotelContract1 = hotelContractService.getHotelContracts().get(0);
        HotelContractDto hotelContract2 = hotelContractService.getHotelContracts().get(1);

        list.add(new RoomPlanSeasonHotelContractDto(1, roomPlanSeason1, hotelContract1));
        list.add(new RoomPlanSeasonHotelContractDto(2, roomPlanSeason2, hotelContract2));

        return list;
    }

    @Override
    public RoomPlanSeasonHotelContractDto getRoomPlanSeasonHotelContractById(int roomPlanSeasonHotelContractId)
            throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createRoomPlanSeasonHotelContract(RoomPlanSeasonHotelContractDto roomPlanSeasonHotelContract)
            throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateRoomPlanSeasonHotelContract(RoomPlanSeasonHotelContractDto roomPlanSeasonHotelContract)
            throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRoomPlanSeasonHotelContract(int idRoomPlanSeasonHotelContract) throws SQLException {
        // TODO Auto-generated method stub

    }

}
