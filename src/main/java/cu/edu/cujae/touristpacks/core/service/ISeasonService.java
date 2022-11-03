package cu.edu.cujae.touristpacks.core.service;

import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.SeasonDto;

public interface ISeasonService {
    List<SeasonDto> getSeasons();

    SeasonDto getSeasonById(int seasonId);

    SeasonDto getSeasonByName(String seasonName);

    void createSeason(SeasonDto season);

    void updateSeason(SeasonDto season);

    void deleteSeason(int id);
}
