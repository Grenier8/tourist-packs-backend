package cu.edu.cujae.touristpacks.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.DiaryActivityDto;
import cu.edu.cujae.touristpacks.core.dto.OtherServiceContractDiaryActivityDto;
import cu.edu.cujae.touristpacks.core.dto.OtherServiceContractDto;
import cu.edu.cujae.touristpacks.core.service.IDiaryActivityService;
import cu.edu.cujae.touristpacks.core.service.IOtherServiceContractDiaryActivityService;
import cu.edu.cujae.touristpacks.core.service.IOtherServiceContractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class OtherServiceContractDiaryActivityServiceImpl implements IOtherServiceContractDiaryActivityService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IOtherServiceContractService otherServiceContractService;

    @Autowired
    private IDiaryActivityService diaryActivityService;

    @Override
    public List<OtherServiceContractDiaryActivityDto> getOtherServiceContractDiaryActivities() throws SQLException {
        List<OtherServiceContractDiaryActivityDto> list = new ArrayList<>();

        String function = "{?= call select_all_other_service_contract_diary_activity()}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            CallableStatement statement = connection.prepareCall(function);
            statement.registerOutParameter(1, Types.OTHER);
            statement.execute();

            ResultSet resultSet = (ResultSet) statement.getObject(1);

            while (resultSet.next()) {
                int idOtherServiceContractDiaryActivity = resultSet.getInt(1);
                int idOtherServiceContract = resultSet.getInt(2);
                int idDiaryActivity = resultSet.getInt(3);
                double activityPrice = resultSet.getDouble(4);

                OtherServiceContractDto otherServiceContract = otherServiceContractService
                        .getOtherServiceContractById(idOtherServiceContract);
                DiaryActivityDto diaryActivity = diaryActivityService.getDiaryActivityById(idDiaryActivity);

                OtherServiceContractDiaryActivityDto dto = new OtherServiceContractDiaryActivityDto(
                        idOtherServiceContractDiaryActivity, activityPrice, otherServiceContract, diaryActivity);
                list.add(dto);
            }
        }

        return list;
    }

    @Override
    public OtherServiceContractDiaryActivityDto getOtherServiceContractDiaryActivityById(
            int idOtherServiceContractDiaryActivity) throws SQLException {
        OtherServiceContractDiaryActivityDto otherServiceContractDiaryActivity = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM other_service_contract_diary_activity where id_other_service_contract_diary_activity = ?");

            pstmt.setInt(1, idOtherServiceContractDiaryActivity);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int idOtherServiceContract = resultSet.getInt(2);
                int idDiaryActivity = resultSet.getInt(3);
                double activityPrice = resultSet.getDouble(4);

                OtherServiceContractDto otherServiceContract = otherServiceContractService
                        .getOtherServiceContractById(idOtherServiceContract);
                DiaryActivityDto diaryActivity = diaryActivityService.getDiaryActivityById(idDiaryActivity);

                otherServiceContractDiaryActivity = new OtherServiceContractDiaryActivityDto(
                        idOtherServiceContractDiaryActivity, activityPrice, otherServiceContract, diaryActivity);
            }
        }

        return otherServiceContractDiaryActivity;
    }

    @Override
    public void createOtherServiceContractDiaryActivity(
            OtherServiceContractDiaryActivityDto otherServiceContractDiaryActivity) throws SQLException {
        String function = "{call other_service_contract_diary_activity_insert(?,?,?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1,
                    otherServiceContractDiaryActivity.getOtherServiceContract().getIdOtherServiceContract());
            statement.setInt(2, otherServiceContractDiaryActivity.getDiaryActivity().getIdDiaryActivity());
            statement.setDouble(3, otherServiceContractDiaryActivity.getActivityPrice());
            statement.execute();
        }

    }

    @Override
    public void updateOtherServiceContractDiaryActivity(
            OtherServiceContractDiaryActivityDto otherServiceContractDiaryActivity) throws SQLException {
        String function = "{call other_service_contract_diary_activity_update(?,?,?,?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, otherServiceContractDiaryActivity.getIdOtherServiceContractDiaryActivity());
            statement.setInt(2,
                    otherServiceContractDiaryActivity.getOtherServiceContract().getIdOtherServiceContract());
            statement.setInt(3, otherServiceContractDiaryActivity.getDiaryActivity().getIdDiaryActivity());
            statement.setDouble(4, otherServiceContractDiaryActivity.getActivityPrice());
            statement.execute();
        }

    }

    @Override
    public void deleteOtherServiceContractDiaryActivity(int idOtherServiceContractDiaryActivity) throws SQLException {
        String function = "{call other_service_contract_diary_activity_delete(?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, idOtherServiceContractDiaryActivity);
            statement.execute();
        }

    }

    @Override
    public List<DiaryActivityDto> getDiaryActivitiesByIdOtherServiceContract(int idOtherServiceContract)
            throws SQLException {
        List<DiaryActivityDto> list = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM other_service_contract_diary_activity where id_other_service_contract = ?");

            pstmt.setInt(1, idOtherServiceContract);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int idDiaryActivity = resultSet.getInt(3);

                list.add(diaryActivityService.getDiaryActivityById(idDiaryActivity));

            }
        }
        return list;
    }

    @Override
    public void deleteOtherServiceContractDiaryActivityByIdOtherServiceContract(int idOtherServiceContract)
            throws SQLException {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM other_service_contract_diary_activity where id_other_service_contract = ?");

            pstmt.setInt(1, idOtherServiceContract);

            pstmt.executeUpdate();

        }

    }

    @Override
    public void deleteHotelDiaryActivityByIds(int idOtherServiceContract, int idDiaryActivity) throws SQLException {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM other_service_contract_diary_activity where id_other_service_contract = ? AND id_activity = ?");

            pstmt.setInt(1, idOtherServiceContract);
            pstmt.setInt(2, idDiaryActivity);

            pstmt.executeUpdate();

        }

    }

}
