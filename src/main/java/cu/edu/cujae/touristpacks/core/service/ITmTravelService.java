package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TmTravelDto;

public interface ITmTravelService {
    List<TmTravelDto> getTmTravels() throws SQLException;

    TmTravelDto getTmTravelById(int tmTravelId) throws SQLException;

    TmTravelDto getTmTravelByName(String tmTravelName) throws SQLException;

    void createTmTravel(TmTravelDto tmTravel) throws SQLException;

    void updateTmTravel(TmTravelDto tmTravel) throws SQLException;

    void deleteTmTravel(int idTmTravel) throws SQLException;
}
