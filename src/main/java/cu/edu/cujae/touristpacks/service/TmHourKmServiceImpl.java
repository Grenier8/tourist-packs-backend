package cu.edu.cujae.touristpacks.service;

import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TmHourKmDto;
import cu.edu.cujae.touristpacks.core.service.ITmHourKmService;

import org.springframework.stereotype.Service;

@Service
public class TmHourKmServiceImpl implements ITmHourKmService {

    @Override
    public List<TmHourKmDto> getTmHourKms() {
        List<TmHourKmDto> tmHourKms = new ArrayList<>();
        tmHourKms.add(new TmHourKmDto("Random", 1, 2, 3, 4));
        return tmHourKms;
    }

    @Override
    public TmHourKmDto getTmHourKmById(int tmHourKmId) {
        return getTmHourKms().stream().filter(r -> r.getIdTmHourKm() == tmHourKmId).findFirst().get();
    }

    @Override
    public TmHourKmDto getTmHourKmByName(String tmHourKmName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createTmHourKm(TmHourKmDto tmHourKm) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateTmHourKm(TmHourKmDto tmHourKm) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteTmHourKm(int idTmHourKm) {
        // TODO Auto-generated method stub

    }

}
