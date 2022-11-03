package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.HotelChainDto;

public interface IHotelChainService {
    List<HotelChainDto> getHotelChains() throws SQLException;

    HotelChainDto getHotelChainById(int hotelChainId) throws SQLException;

    HotelChainDto getHotelChainByName(String hotelChainName) throws SQLException;

    void createHotelChain(HotelChainDto hotelChain) throws SQLException;

    void updateHotelChain(HotelChainDto hotelChain) throws SQLException;

    void deleteHotelChain(int id) throws SQLException;
}
