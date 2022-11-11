package cu.edu.cujae.touristpacks.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.DiaryActivityDto;
import cu.edu.cujae.touristpacks.core.service.IDiaryActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DiaryActivityServiceImpl implements IDiaryActivityService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<DiaryActivityDto> getDiaryActivities() throws SQLException {
        List<DiaryActivityDto> list = new ArrayList<>();

        String function = "{?= call select_all_diary_activity()}";

        Connection connection = jdbcTemplate.getDataSource().getConnection();
        connection.setAutoCommit(false);
        CallableStatement statement = connection.prepareCall(function);
        statement.registerOutParameter(1, Types.OTHER);
        statement.execute();

        ResultSet resultSet = (ResultSet) statement.getObject(1);

        while (resultSet.next()) {
            int idActivity = resultSet.getInt(1);
            LocalDate date = resultSet.getDate(2).toLocalDate();
            String activityDescription = resultSet.getString(3);
            String activityName = resultSet.getString(4);

            DiaryActivityDto diaryActivity = new DiaryActivityDto(idActivity, activityName, date,
                    activityDescription);
            list.add(diaryActivity);
        }

        return list;
    }

    @Override
    public DiaryActivityDto getDiaryActivityById(int idDiaryActivity) throws SQLException {
        DiaryActivityDto diaryActivity = null;

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM diary_activity where id_activity = ?");

        pstmt.setInt(1, idDiaryActivity);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            LocalDate date = resultSet.getDate(2).toLocalDate();
            String activityDescription = resultSet.getString(3);
            String activityName = resultSet.getString(4);

            diaryActivity = new DiaryActivityDto(idDiaryActivity, activityName, date, activityDescription);
        }

        return diaryActivity;
    }

    @Override
    public DiaryActivityDto getDiaryActivityByName(String diaryActivityName) throws SQLException {
        DiaryActivityDto diaryActivity = null;

        PreparedStatement pstmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(
                "SELECT * FROM diary_activity where activity_name = ?");

        pstmt.setString(1, diaryActivityName);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            int idDiaryActivity = resultSet.getInt(1);
            LocalDate date = resultSet.getDate(2).toLocalDate();
            String activityDescription = resultSet.getString(3);

            diaryActivity = new DiaryActivityDto(idDiaryActivity, diaryActivityName, date, activityDescription);
        }

        return diaryActivity;
    }

    @Override
    public void createDiaryActivity(DiaryActivityDto diaryActivity) throws SQLException {
        String function = "{call diary_activity_insert(?,?,?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setDate(1, Date.valueOf(diaryActivity.getDate()));
        statement.setString(2, diaryActivity.getDescription());
        statement.setString(3, diaryActivity.getDiaryActivityName());
        statement.execute();

    }

    @Override
    public void updateDiaryActivity(DiaryActivityDto diaryActivity) throws SQLException {
        String function = "{call diary_activity_update(?,?,?,?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setInt(1, diaryActivity.getIdDiaryActivity());
        statement.setDate(2, Date.valueOf(diaryActivity.getDate()));
        statement.setString(3, diaryActivity.getDescription());
        statement.setString(4, diaryActivity.getDiaryActivityName());
        statement.execute();
    }

    @Override
    public void deleteDiaryActivity(int idDiaryActivity) throws SQLException {
        String function = "{call diary_activity_delete(?)}";

        CallableStatement statement = jdbcTemplate.getDataSource().getConnection().prepareCall(function);
        statement.setInt(1, idDiaryActivity);
        statement.execute();
    }

}
