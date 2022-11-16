package cu.edu.cujae.touristpacks.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.service.IDiaryActivityTouristPackService;
import cu.edu.cujae.touristpacks.core.dto.DiaryActivityDto;
import cu.edu.cujae.touristpacks.core.dto.DiaryActivityTouristPackDto;
import cu.edu.cujae.touristpacks.core.dto.TouristPackDto;
import cu.edu.cujae.touristpacks.core.service.IDiaryActivityService;
import cu.edu.cujae.touristpacks.core.service.ITouristPackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DiaryActivityTouristPackServiceImpl implements IDiaryActivityTouristPackService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    IDiaryActivityService diaryActivityService;

    @Autowired
    ITouristPackService touristPackService;

    @Override
    public List<DiaryActivityTouristPackDto> getDiaryActivityTouristPacks() throws SQLException {
        List<DiaryActivityTouristPackDto> list = new ArrayList<>();

        String function = "{?= call select_all_diary_activity_turist_pack()}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            CallableStatement statement = connection.prepareCall(function);
            statement.registerOutParameter(1, Types.OTHER);
            statement.execute();

            ResultSet resultSet = (ResultSet) statement.getObject(1);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int idActivity = resultSet.getInt(2);
                int idPack = resultSet.getInt(3);

                DiaryActivityDto diaryActivity = diaryActivityService.getDiaryActivityById(idActivity);
                TouristPackDto touristPack = touristPackService.getTouristPackById(idPack);

                DiaryActivityTouristPackDto dto = new DiaryActivityTouristPackDto(id, diaryActivity, touristPack);
                list.add(dto);
            }
        }
        return list;
    }

    @Override
    public DiaryActivityTouristPackDto getDiaryActivityTouristPackById(int idDiaryActivityTouristPack)
            throws SQLException {
        DiaryActivityTouristPackDto diaryActivityTouristPack = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM diary_activity_turist_pack where id_diary_activity_turist_pack = ?");

            pstmt.setInt(1, idDiaryActivityTouristPack);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int idDiaryActivity = resultSet.getInt(2);
                int idTouristPack = resultSet.getInt(3);

                DiaryActivityDto diaryActivity = diaryActivityService.getDiaryActivityById(idDiaryActivity);
                TouristPackDto touristPack = touristPackService.getTouristPackById(idTouristPack);

                diaryActivityTouristPack = new DiaryActivityTouristPackDto(idDiaryActivityTouristPack, diaryActivity,
                        touristPack);
            }
        }

        return diaryActivityTouristPack;
    }

    @Override
    public void createDiaryActivityTouristPack(DiaryActivityTouristPackDto diaryActivityTouristPack)
            throws SQLException {
        String function = "{call diary_activity_turist_pack_insert(?,?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, diaryActivityTouristPack.getDiaryActivity().getIdDiaryActivity());
            statement.setInt(2, diaryActivityTouristPack.getTouristPack().getIdTouristPack());
            statement.execute();
        }

    }

    @Override
    public void updateDiaryActivityTouristPack(DiaryActivityTouristPackDto diaryActivityTouristPack)
            throws SQLException {
        String function = "{call diary_activity_turist_pack_update(?,?,?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, diaryActivityTouristPack.getIdDiaryActivityTouristPack());
            statement.setInt(2, diaryActivityTouristPack.getDiaryActivity().getIdDiaryActivity());
            statement.setInt(3, diaryActivityTouristPack.getTouristPack().getIdTouristPack());
            statement.execute();
        }

    }

    @Override
    public void deleteDiaryActivityTouristPack(int idDiaryActivityTouristPack) throws SQLException {
        String function = "{call diary_activity_turist_pack_delete(?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, idDiaryActivityTouristPack);
            statement.execute();
        }

    }

}
