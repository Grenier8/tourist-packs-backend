package cu.edu.cujae.touristpacks.core.service;

import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TmTravelDto;

public interface ITmTravelService {
    List<TmTravelDto> getTmTravels();

    TmTravelDto getTmTravelById(int tmTravelId);

    TmTravelDto getTmTravelByName(String tmTravelName);

    void createTmTravel(TmTravelDto tmTravel);

    void updateTmTravel(TmTravelDto tmTravel);

    void deleteTmTravel(int idTmTravel);
}
