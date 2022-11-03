package cu.edu.cujae.touristpacks.service;

import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TmTravelDto;
import cu.edu.cujae.touristpacks.core.service.ITmTravelService;

import org.springframework.stereotype.Service;

@Service
public class TmTravelServiceImpl implements ITmTravelService {

    @Override
    public List<TmTravelDto> getTmTravels() {
        List<TmTravelDto> tmTravel = new ArrayList<>();
        tmTravel.add(new TmTravelDto(1, "Name 1", "Description 1", 1, 2));
        return tmTravel;
    }

    @Override
    public TmTravelDto getTmTravelById(int tmTravelId) {
        return getTmTravels().stream().filter(r -> r.getIdTmTravel() == tmTravelId).findFirst().get();
    }

    @Override
    public TmTravelDto getTmTravelByName(String tmTravelName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createTmTravel(TmTravelDto tmTravel) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateTmTravel(TmTravelDto tmTravel) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteTmTravel(int idTmTravel) {
        // TODO Auto-generated method stub

    }

}
