package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TmHourKmDto;

public interface ITmHourKmService {
    List<TmHourKmDto> getTmHourKms() throws SQLException;

    TmHourKmDto getTmHourKmById(int tmHourKmId) throws SQLException;

    TmHourKmDto getTmHourKmByName(String tmHourKmName) throws SQLException;

    void createTmHourKm(TmHourKmDto tmHourKm) throws SQLException;

    void updateTmHourKm(TmHourKmDto tmHourKm) throws SQLException;

    void deleteTmHourKm(int idTmHourKm) throws SQLException;
}
