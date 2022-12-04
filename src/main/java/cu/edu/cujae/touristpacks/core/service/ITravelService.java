package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TravelDto;

public interface ITravelService {
    List<TravelDto> getTravels() throws SQLException;

    TravelDto getTravelById(int travelId) throws SQLException;

    TravelDto getTravelByName(String travelName) throws SQLException;

    void createTravel(TravelDto travel) throws SQLException;

    void updateTravel(TravelDto travel) throws SQLException;

    void deleteTravel(int idTravel) throws SQLException;
}
