package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.SeasonDto;

public interface ISeasonService {
    List<SeasonDto> getSeasons() throws SQLException;

    SeasonDto getSeasonById(int seasonId) throws SQLException;

    SeasonDto getSeasonByName(String seasonName) throws SQLException;

    void createSeason(SeasonDto season) throws SQLException;

    void updateSeason(SeasonDto season) throws SQLException;

    void deleteSeason(int id) throws SQLException;
}
