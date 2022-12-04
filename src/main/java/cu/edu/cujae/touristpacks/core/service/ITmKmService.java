package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TmKmDto;

public interface ITmKmService {
    List<TmKmDto> getTmKms() throws SQLException;

    TmKmDto getTmKmById(int tmKmId) throws SQLException;

    TmKmDto getTmKmByName(String tmKmName) throws SQLException;

    void createTmKm(TmKmDto tmKm) throws SQLException;

    void updateTmKm(TmKmDto tmKm) throws SQLException;

    void deleteTmKm(int idTmKm) throws SQLException;
}
