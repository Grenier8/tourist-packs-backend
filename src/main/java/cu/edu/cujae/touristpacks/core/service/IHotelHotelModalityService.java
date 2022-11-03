package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.HotelHotelModalityDto;

public interface IHotelHotelModalityService {
    List<HotelHotelModalityDto> getHotelHotelModalities() throws SQLException;

    HotelHotelModalityDto getHotelHotelModalityById(int hotelHotelModalityId) throws SQLException;

    void createHotelHotelModality(HotelHotelModalityDto hotelHotelModality) throws SQLException;

    void updateHotelHotelModality(HotelHotelModalityDto hotelHotelModality) throws SQLException;

    void deleteHotelHotelModality(int idHotelHotelModality) throws SQLException;
}
