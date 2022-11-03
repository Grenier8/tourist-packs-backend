package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.HotelDto;

public interface IHotelService {
    List<HotelDto> getHotels() throws SQLException;

    HotelDto getHotelById(int hotelId) throws SQLException;

    HotelDto getHotelByName(String hotelName) throws SQLException;

    void createHotel(HotelDto hotel) throws SQLException;

    void updateHotel(HotelDto hotel) throws SQLException;

    void deleteHotel(int id) throws SQLException;
}
