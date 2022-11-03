package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TouristPackDto;

public interface ITouristPackService {
    List<TouristPackDto> getTouristPacks() throws SQLException;

    TouristPackDto getTouristPackById(int touristPackId) throws SQLException;

    TouristPackDto getTouristPackByName(String touristPackName) throws SQLException;

    void createTouristPack(TouristPackDto touristPack) throws SQLException;

    void updateTouristPack(TouristPackDto touristPack) throws SQLException;

    void deleteTouristPack(int idTouristPack) throws SQLException;
}
