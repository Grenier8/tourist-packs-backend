package cu.edu.cujae.touristpacks.core.service;

import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TravelDto;

public interface ITravelService {
    List<TravelDto> getTravels();

    TravelDto getTravelById(int travelId);

    TravelDto getTravelByName(String travelName);

    void createTravel(TravelDto travel);

    void updateTravel(TravelDto travel);

    void deleteTravel(int idTravel);
}
