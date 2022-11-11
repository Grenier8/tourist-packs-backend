package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.HotelContractDto;

public interface IHotelContractService {
    List<HotelContractDto> getHotelContracts() throws SQLException;

    HotelContractDto getHotelContractById(int hotelContractId) throws SQLException;

    HotelContractDto getHotelContractByTitle(String hotelContractName) throws SQLException;

    void createHotelContract(HotelContractDto hotelContract) throws SQLException;

    void updateHotelContract(HotelContractDto hotelContract) throws SQLException;

    void deleteHotelContract(int idHotelContract) throws SQLException;
}
