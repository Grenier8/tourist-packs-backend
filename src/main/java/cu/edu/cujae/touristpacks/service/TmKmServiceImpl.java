
package cu.edu.cujae.touristpacks.service;

import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TmKmDto;
import cu.edu.cujae.touristpacks.core.service.ITmKmService;

import org.springframework.stereotype.Service;

@Service
public class TmKmServiceImpl implements ITmKmService {

    @Override
    public List<TmKmDto> getTmKms() {
        List<TmKmDto> tmKms = new ArrayList<>();
        tmKms.add(new TmKmDto(1, "Random 1", 1, 2, 3, 4));
        return tmKms;
    }

    @Override
    public TmKmDto getTmKmById(int tmKmId) {
        return getTmKms().stream().filter(r -> r.getIdTmKm() == tmKmId).findFirst().get();
    }

    @Override
    public TmKmDto getTmKmByName(String tmKmName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createTmKm(TmKmDto tmKm) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateTmKm(TmKmDto tmKm) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteTmKm(int idTmKm) {
        // TODO Auto-generated method stub

    }

}
