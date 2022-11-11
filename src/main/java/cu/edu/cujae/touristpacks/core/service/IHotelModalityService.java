package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.HotelModalityDto;

public interface IHotelModalityService {
    List<HotelModalityDto> getHotelModalities() throws SQLException;

    HotelModalityDto getHotelModalityById(int hotelModalityId) throws SQLException;

    HotelModalityDto getHotelModalityByName(String hotelModalityName) throws SQLException;

    void createHotelModality(HotelModalityDto hotelModality) throws SQLException;

    void updateHotelModality(HotelModalityDto hotelModality) throws SQLException;

    void deleteHotelModality(int idHotelModality) throws SQLException;
}
