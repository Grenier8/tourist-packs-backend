package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.DiaryActivityDto;

public interface IDiaryActivityService {
    List<DiaryActivityDto> getDiaryActivities() throws SQLException;

    DiaryActivityDto getDiaryActivityById(int diaryActivityId) throws SQLException;

    DiaryActivityDto getDiaryActivityByName(String diaryActivityName) throws SQLException;

    void createDiaryActivity(DiaryActivityDto diaryActivity) throws SQLException;

    void updateDiaryActivity(DiaryActivityDto diaryActivity) throws SQLException;

    void deleteDiaryActivity(int idDiaryActivity) throws SQLException;
}
