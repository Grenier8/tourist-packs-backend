package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.DiaryActivityDto;
import cu.edu.cujae.touristpacks.core.dto.DiaryActivityTouristPackDto;

public interface IDiaryActivityTouristPackService {
    List<DiaryActivityTouristPackDto> getDiaryActivityTouristPacks() throws SQLException;

    DiaryActivityTouristPackDto getDiaryActivityTouristPackById(int diaryActivityTouristPackId) throws SQLException;

    void createDiaryActivityTouristPack(DiaryActivityTouristPackDto diaryActivityTouristPack) throws SQLException;

    void updateDiaryActivityTouristPack(DiaryActivityTouristPackDto diaryActivityTouristPack) throws SQLException;

    void deleteDiaryActivityTouristPack(int idDiaryActivityTouristPack) throws SQLException;

    List<DiaryActivityDto> getDiaryActivitiesByIdTouristPack(int idTouristPack) throws SQLException;

    void deleteDiaryActivityTouristPackByIds(int idTouristPack, int idDiaryActivity) throws SQLException;

    void deleteDiaryActivityTouristPackByIdTouristPack(int idTouristPack) throws SQLException;
}
