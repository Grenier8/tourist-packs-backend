package cu.edu.cujae.touristpacks.service;

import cu.edu.cujae.touristpacks.core.dto.AlimentaryPlanDto;
import cu.edu.cujae.touristpacks.core.service.IAlimentaryPlanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlimentaryPlanServiceImpl implements IAlimentaryPlanService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AlimentaryPlanDto> getAlimentaryPlans() throws SQLException {
        List<AlimentaryPlanDto> list = new ArrayList<>();

        String function = "{?= call select_all_alimentary_plan()}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            CallableStatement statement = connection.prepareCall(function);
            statement.registerOutParameter(1, Types.OTHER);
            statement.execute();

            ResultSet resultSet = (ResultSet) statement.getObject(1);

            while (resultSet.next()) {
                int idPlan = resultSet.getInt(1);
                String planName = resultSet.getString(2);

                AlimentaryPlanDto dto = new AlimentaryPlanDto(idPlan, planName);
                list.add(dto);
            }
        }
        return list;
    }

    @Override
    public AlimentaryPlanDto getAlimentaryPlanById(int idAlimentaryPlan) throws SQLException {
        AlimentaryPlanDto alimentaryPlan = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM alimentary_plan where id_plan = ?");

            pstmt.setInt(1, idAlimentaryPlan);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String alimentaryPlanName = resultSet.getString(2);

                alimentaryPlan = new AlimentaryPlanDto(idAlimentaryPlan, alimentaryPlanName);
            }
        }
        return alimentaryPlan;

    }

    @Override
    public AlimentaryPlanDto getAlimentaryPlanByName(String alimentaryPlanName) throws SQLException {
        AlimentaryPlanDto alimentaryPlan = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM alimentary_plan where plan_name = ?");

            pstmt.setString(1, alimentaryPlanName);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int idAlimentaryPlan = resultSet.getInt(2);

                alimentaryPlan = new AlimentaryPlanDto(idAlimentaryPlan, alimentaryPlanName);
            }
        }
        return alimentaryPlan;
    }

    @Override
    public void createAlimentaryPlan(AlimentaryPlanDto alimentaryPlan) throws SQLException {
        String function = "{call alimentary_plan_insert(?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setString(1, alimentaryPlan.getAlimentaryPlanName());
            statement.execute();
        }
    }

    @Override
    public void updateAlimentaryPlan(AlimentaryPlanDto alimentaryPlan) throws SQLException {
        String function = "{call alimentary_plan_update(?,?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, alimentaryPlan.getIdAlimentaryPlan());
            statement.setString(2, alimentaryPlan.getAlimentaryPlanName());
            statement.execute();
        }
    }

    @Override
    public void deleteAlimentaryPlan(int idAlimentaryPlan) throws SQLException {
        String function = "{call alimentary_plan_delete(?)}";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement statement = connection.prepareCall(function);
            statement.setInt(1, idAlimentaryPlan);
            statement.execute();
        }
    }

}
