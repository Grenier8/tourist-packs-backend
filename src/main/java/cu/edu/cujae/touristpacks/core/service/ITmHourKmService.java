package cu.edu.cujae.touristpacks.core.service;

import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.TmHourKmDto;

public interface ITmHourKmService {
    List<TmHourKmDto> getTmHourKms();

    TmHourKmDto getTmHourKmById(int tmHourKmId);

    TmHourKmDto getTmHourKmByName(String tmHourKmName);

    void createTmHourKm(TmHourKmDto tmHourKm);

    void updateTmHourKm(TmHourKmDto tmHourKm);

    void deleteTmHourKm(int idTmHourKm);
}
