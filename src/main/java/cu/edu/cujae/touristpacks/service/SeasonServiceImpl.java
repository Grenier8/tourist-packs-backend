package cu.edu.cujae.touristpacks.service;

import cu.edu.cujae.touristpacks.core.dto.SeasonDto;
import cu.edu.cujae.touristpacks.core.service.ISeasonService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeasonServiceImpl implements ISeasonService {

    @Override
    public List<SeasonDto> getSeasons() {
        List<SeasonDto> seasons = new ArrayList<>();
        seasons.add(
                new SeasonDto(1, "Baja", LocalDate.of(2021, 6, 1), LocalDate.of(2021, 10, 31), "Precios mas baratos"));
        seasons.add(new SeasonDto(2, "Alta", LocalDate.of(2021, 1, 1), LocalDate.of(2021, 5, 31), "Precios altos"));
        return seasons;
    }

    @Override
    public SeasonDto getSeasonById(int seasonId) {
        return getSeasons().stream().filter(r -> r.getIdSeason() == seasonId).findFirst().get();
    }

    @Override
    public void createSeason(SeasonDto season) {

    }

    @Override
    public void updateSeason(SeasonDto season) {

    }

    @Override
    public void deleteSeason(int id) {

    }

    @Override
    public SeasonDto getSeasonByName(String seasonName) {
        // TODO Auto-generated method stub
        return null;
    }
}
