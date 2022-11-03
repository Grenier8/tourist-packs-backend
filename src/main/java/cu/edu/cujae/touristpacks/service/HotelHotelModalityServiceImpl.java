package cu.edu.cujae.touristpacks.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.service.IHotelService;
import cu.edu.cujae.touristpacks.core.dto.HotelDto;
import cu.edu.cujae.touristpacks.core.dto.HotelHotelModalityDto;
import cu.edu.cujae.touristpacks.core.dto.HotelModalityDto;
import cu.edu.cujae.touristpacks.core.service.IHotelHotelModalityService;
import cu.edu.cujae.touristpacks.core.service.IHotelModalityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelHotelModalityServiceImpl implements IHotelHotelModalityService {

    @Autowired
    IHotelService hotelService;

    @Autowired
    IHotelModalityService hotelModalityService;

    @Override
    public List<HotelHotelModalityDto> getHotelHotelModalities() throws SQLException {
        List<HotelHotelModalityDto> list = new ArrayList<>();

        HotelDto hotel1 = hotelService.getHotels().get(0);
        HotelDto hotel2 = hotelService.getHotels().get(1);

        HotelModalityDto hotelModality1 = hotelModalityService.getHotelModalities().get(0);
        HotelModalityDto hotelModality2 = hotelModalityService.getHotelModalities().get(1);

        list.add(new HotelHotelModalityDto(1, hotel1, hotelModality1));
        list.add(new HotelHotelModalityDto(2, hotel2, hotelModality2));

        return list;
    }

    @Override
    public HotelHotelModalityDto getHotelHotelModalityById(int hotelHotelModalityId) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createHotelHotelModality(HotelHotelModalityDto hotelHotelModality) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateHotelHotelModality(HotelHotelModalityDto hotelHotelModality) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteHotelHotelModality(int idHotelHotelModality) throws SQLException {
        // TODO Auto-generated method stub

    }

}
