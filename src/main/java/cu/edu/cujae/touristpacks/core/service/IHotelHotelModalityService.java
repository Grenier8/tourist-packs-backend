package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.HotelHotelModalityDto;
import cu.edu.cujae.touristpacks.core.dto.HotelModalityDto;

public interface IHotelHotelModalityService {
    List<HotelHotelModalityDto> getHotelHotelModalities() throws SQLException;

    HotelHotelModalityDto getHotelHotelModalityById(int hotelHotelModalityId) throws SQLException;

    void createHotelHotelModality(HotelHotelModalityDto hotelHotelModality) throws SQLException;

    void updateHotelHotelModality(HotelHotelModalityDto hotelHotelModality) throws SQLException;

    void deleteHotelHotelModality(int idHotelHotelModality) throws SQLException;

    void deleteHotelHotelModalityByIdHotel(int idHotel) throws SQLException;

    List<HotelModalityDto> getHotelModalitiesByIdHotel(int idHotel) throws SQLException;

    void deleteHotelHotelModalityByIds(int idHotel, int idHotelModality) throws SQLException;
}
